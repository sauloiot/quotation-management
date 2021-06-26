package com.management.quotation.repositories;

import com.management.quotation.models.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface StockRepository extends JpaRepository<Stock, Integer> {
    @Transactional(readOnly = true)
    Stock findByStockId(String stockId);
}
