package com.management.quotation.repositories;

import com.management.quotation.models.Stock;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class StockRepositoryTest {
    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Autowired
    private StockRepository repository;

    @Test
    void testSave() throws ParseException {
        Stock st = new Stock(1, "petr5");
        Stock stock = repository.save(st);
        assertThat(stock).isNotNull();
    }
    @Test
    void testFindById(){
        Optional<Stock> quote = repository.findById(1);
        assertThat(quote).isNotNull();
    }
    @Test
    void testFindAll(){
        List<Stock> quotes = repository.findAll();
        assertThat(quotes).isNotNull();
    }
}
