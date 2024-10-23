package com.zara.price.domain.services;

import com.zara.price.domain.model.Price;
import com.zara.price.domain.PriceSearchCriteria;
import reactor.core.publisher.Mono;

public interface PriceService {
    Mono<Price> calculatePrice(PriceSearchCriteria criteria);
}

