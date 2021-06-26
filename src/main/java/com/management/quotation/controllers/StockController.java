package com.management.quotation.controllers;

import com.management.quotation.models.Quote;
import com.management.quotation.models.Stock;
import com.management.quotation.services.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/")
public class StockController {

    @Autowired
    StockService stockService;

    @RequestMapping( method= RequestMethod.GET)
    public ResponseEntity<List<Stock>> findAll() {
        List<Stock> list = stockService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<Stock> findById(@PathVariable Integer id) {
        Stock obj = stockService.findById(id);

        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(value="/stockid/{stockid}", method=RequestMethod.GET)
    public ResponseEntity<Stock> findByCodigo(@PathVariable String stockid) {
        Stock obj = stockService.findByStockId(stockid);

        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@RequestBody Stock obj){
        obj = stockService.insert(obj);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(obj.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }


}
