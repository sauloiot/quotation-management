package com.management.quotation.services;

import com.management.quotation.models.Stock;
import com.management.quotation.repositories.StockRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class StockServiceTest {
    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }


    @InjectMocks
    StockService stockService;

    @Mock
    StockRepository stockRepository;

    @Test
    public void findById() {

        when(stockRepository.findById(1)).thenReturn(java.util.Optional.of(new Stock(1, "petr4")));

        Stock stock = stockService.findById(1);
        assertEquals(1, stock.getId());
        assertEquals("petr4", stock.getStockId());

    }

    @Test
    public void findAll()
    {
        List<Stock> list = new ArrayList<Stock>();
        Stock stock1 = new Stock(1, "petr4");
        Stock stock2 = new Stock(2, "petr5");
        Stock stock3 = new Stock(3, "petr6");

        list.add(stock1);
        list.add(stock2);
        list.add(stock3);

        when(stockRepository.findAll()).thenReturn(list);

        //test
        List<Stock> empList = stockService.findAll();

        assertEquals(3, empList.size());
        verify(stockRepository, times(1)).findAll();
    }

}
