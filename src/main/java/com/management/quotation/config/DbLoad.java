package com.management.quotation.config;

import com.management.quotation.models.Quote;
import com.management.quotation.models.Stock;
import com.management.quotation.repositories.QuoteRepository;
import com.management.quotation.repositories.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Arrays;

@Configuration
public class DbLoad implements CommandLineRunner {
    @Autowired
    QuoteRepository quoteRepository;
    @Autowired
    StockRepository stockRepository;

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    public void run(String... args) throws Exception {
        Stock stock1 = new Stock(null, "petr4");
        Quote quote1 = new Quote(null, sdf.parse("25/07/2018"), 35.00, stock1);
        Quote quote2 = new Quote(null, sdf.parse("21/04/2019"), 44.00, stock1);
        Quote quote3 = new Quote(null, sdf.parse("15/08/2020"), 68.00, stock1);
        Quote quote4 = new Quote(null, sdf.parse("25/04/2021"), 121.00, stock1);

        stock1.getQuotes().addAll(Arrays.asList(quote1, quote2, quote3, quote4));

        stockRepository.saveAll(Arrays.asList(stock1));
        quoteRepository.saveAll(Arrays.asList(quote1, quote2, quote3, quote4));


    }
}
