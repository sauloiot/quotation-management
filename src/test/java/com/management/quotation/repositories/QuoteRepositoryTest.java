package com.management.quotation.repositories;

import com.management.quotation.models.Quote;
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
public class QuoteRepositoryTest {
    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Autowired
    private QuoteRepository repository;

    @Test
    void testSave() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Quote qu = new Quote(1, sdf.parse("25/04/2021"), 35.00, null);
        Quote quote = repository.save(qu);
        assertThat(quote).isNotNull();
    }
    @Test
    void testFindById(){
        Optional<Quote> quote = repository.findById(1);
        assertThat(quote).isNotNull();
    }
    @Test
    void testFindAll(){
        List<Quote> quotes = repository.findAll();
        assertThat(quotes).isNotNull();
    }
}
