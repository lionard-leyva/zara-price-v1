package com.zara.price.domain;

import java.time.LocalDateTime;

public record PriceSearchCriteria
        (Long productId,
         Long brandId,
         LocalDateTime startDate,
         LocalDateTime endDate) {}