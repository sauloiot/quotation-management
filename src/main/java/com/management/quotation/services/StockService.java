package com.management.quotation.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.management.quotation.models.Quote;
import com.management.quotation.models.Stock;
import com.management.quotation.repositories.QuoteRepository;
import com.management.quotation.repositories.StockRepository;
import com.management.quotation.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class StockService {
    @Autowired
    StockRepository stockRepository;
    @Autowired
    QuoteRepository quoteRepository;

    public Stock findById(Integer id) {
        Optional<Stock> obj = stockRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Object not found! Id: " + id + ", Type: " + Stock.class.getSimpleName()));
    }

    public Stock findByStockId(String stockId){

        Stock obj = stockRepository.findByStockId(stockId);
        if (obj == null){
            throw new ObjectNotFoundException(
                    "Stock not found! StockId: " + stockId + ", Type: " + Stock.class.getSimpleName());
        }
        return obj;


    }

    public List<Stock> findAll(){
        return stockRepository.findAll();
    }

    public Stock insert(Stock stock) {
                RestTemplate restTemplate = new RestTemplate();
        String stockApiUrl
                = "http://localhost:8080/stock/";
        ResponseEntity<String> response
                = restTemplate.getForEntity(stockApiUrl + stock.getStockId(), String.class);

        if (response.getBody() != null){
            System.out.println(response);
            Stock stockId = stockRepository.findByStockId(stock.getStockId());
            if (stockId == null){
                stock = stockRepository.save(stock);
            }else{
                stock.setId(stockId.getId());
            }
            for (Quote quote : stock.getQuotes()) {
                Quote obj = new Quote();
                obj.setStock(stock);
                obj.setDate(quote.getDate());
                obj.setPrice(quote.getPrice());
                quoteRepository.save(obj);
            }
            return stock;
        }else{
            throw new ObjectNotFoundException(
                    "StockId: '"+ stock.getStockId() + "' not found on api '"+ stockApiUrl +"' ! Type: " + Stock.class.getSimpleName());
        }

        }

    }
