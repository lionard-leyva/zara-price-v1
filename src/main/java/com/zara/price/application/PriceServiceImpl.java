package com.zara.price.application;

import com.zara.price.domain.PriceSearchCriteria;
import com.zara.price.domain.PriceValidator;
import com.zara.price.domain.model.Price;
import com.zara.price.domain.services.PriceService;
import com.zara.price.exception.PriceCalculationException;
import com.zara.price.infraestructure.repository.PriceRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class PriceServiceImpl implements PriceService {

    private final PriceRepository priceRepository;


    public PriceServiceImpl(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Override
    public Mono<Price> calculatePrice(PriceSearchCriteria criteria) {
        return priceRepository
                .findByCriteria(criteria)
                .next()
                .switchIfEmpty(Mono.error(new PriceCalculationException("Price not found for the given criteria.")))
                .doOnNext(PriceValidator::validate);
    }
}