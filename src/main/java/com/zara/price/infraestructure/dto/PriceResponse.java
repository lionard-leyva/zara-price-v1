package com.zara.price.infraestructure.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PriceResponse(Long productId,
                            Long brandId,
                            BigDecimal price,
                            String currency,
                            LocalDateTime startDate,
                            LocalDateTime endDate) {
}
