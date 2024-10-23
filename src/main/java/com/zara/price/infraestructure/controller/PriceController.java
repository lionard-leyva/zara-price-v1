package com.zara.price.infraestructure.controller;

import com.zara.price.domain.PriceSearchCriteria;
import com.zara.price.domain.services.PriceService;
import com.zara.price.infraestructure.dto.PriceResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Controller class for prices operations.
 *
 * @author Lionard Leyva Hurtado
 * @version 1.0
 */
@RestController
@RequestMapping("/api/v1/prices")
@Tag(name = "Price", description = "Price management APIs")
public class PriceController {

    private final PriceService priceService;

    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @Operation(summary = "Get price by product, brand, and date")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Price retrieved successfully", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Bad Request - Invalid input data", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "422", description = "Unprocessable Entity - Invalid input data", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "403", description = "Unauthorized - Invalid credentials", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Not Found - Price not available", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal Server Error - Problem retrieving the price", content = @Content(mediaType = "application/json"))
    })
    @GetMapping(value = "/{productId}/{brandId}/{dateTime}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<PriceResponse>> getPrice(
            @PathVariable Long productId,
            @PathVariable Long brandId,
            @Parameter(description = "Date and time in ISO format, e.g., '2024-06-14T10:00:00'.", example = "2024-06-14T10:00:00")
            @PathVariable
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) String dateTime) {

        LocalDateTime date = LocalDateTime.parse(dateTime, DateTimeFormatter.ISO_DATE_TIME);
        PriceSearchCriteria priceSearchCriteria = new PriceSearchCriteria(productId, brandId, date, date);

        return priceService.calculatePrice(priceSearchCriteria)
                .map(price -> ResponseEntity.ok(new PriceResponse(
                        price.getProductId(),
                        price.getBrandId(),
                        price.getPrices(),
                        price.getCurr(),
                        price.getStartDate(),
                        price.getEndDate())))
                ;
    }
}