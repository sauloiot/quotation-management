package com.management.quotation.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.management.quotation.models.Stock;
import com.management.quotation.repositories.StockRepository;
import com.management.quotation.services.StockService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(StockController.class)
public class StockControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StockService stockService;
    @MockBean
    private StockRepository stockRepository;

    @InjectMocks
    private StockController stockController;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findAll() throws Exception {

        Stock stock1 = new Stock(1, "petr4");
        Stock stock2 = new Stock(2, "petr5");

        List<Stock> stocks = Arrays.asList(stock1, stock2);
        given(stockService.findAll()).willReturn(stocks);

        mockMvc.perform(get("/")).andExpect(status().isOk())
                .andExpect(content().json("[{\"id\": 1, \"stockId\": \"petr4\"}, {\"id\": 2, \"stockId\": \"petr5\"}]"));

    }

    @Test
    public void findById() throws Exception {
        when(stockService.findById(1)).thenReturn(new Stock(1, "petr4"));
        mockMvc.perform( MockMvcRequestBuilders
                .get("/{id}", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.stockId").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.stockId").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.stockId").value("petr4"));

    }

    @Test
    public void insert() throws Exception{
        Stock stock = new Stock(1, "petr4");
        when(stockService.insert(any())).thenReturn(stock);
        mockMvc.perform(post("/").
                contentType(MediaType.APPLICATION_JSON).
                content(asJsonString(stock))).
                andExpect(status().isCreated());
        verify(stockService,times(1)).insert(any());
    }

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
