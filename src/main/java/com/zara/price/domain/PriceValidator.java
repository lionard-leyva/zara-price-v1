package com.zara.price.domain;

import com.zara.price.domain.model.Price;

import java.math.BigDecimal;

public class PriceValidator {
    private PriceValidator() {
    }

    public static void validate(Price price) {
        if (price.getPrices().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor que 0");
        }
        if (price.getStartDate() == null || price.getEndDate() == null) {
            throw new IllegalArgumentException("Las fechas de inicio y fin no pueden ser nulas");
        }
        if (price.getStartDate().isAfter(price.getEndDate())) {
            throw new IllegalArgumentException("La fecha de inicio debe ser anterior a la fecha de fin");
        }
    }
}