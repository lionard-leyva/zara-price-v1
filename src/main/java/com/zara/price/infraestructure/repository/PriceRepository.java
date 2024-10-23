package com.zara.price.infraestructure.repository;

import com.zara.price.domain.PriceSearchCriteria;
import com.zara.price.domain.model.Price;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface PriceRepository extends ReactiveCrudRepository<Price, Long> {
    @Query(value = "SELECT * FROM prices WHERE product_id = :#{#criteria.productId} " +
            "AND brand_id = :#{#criteria.brandId} " +
            "AND start_date <= :#{#criteria.endDate} " +
            "AND end_date >= :#{#criteria.startDate} " +
            "ORDER BY priority DESC")
    Flux<Price> findByCriteria(PriceSearchCriteria criteria);
}