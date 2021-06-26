package com.management.quotation.models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class StockTest {
    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testModelCategoria() throws ParseException {
        Stock stock = new Stock(1, "petr4");
        assertThat(stock)
                .isNotNull()
                .satisfies(u -> {
                    assertThat(u.getId()).isEqualTo(1);
                    assertThat(u.getStockId()).isEqualTo("petr4");
                });
    }
}