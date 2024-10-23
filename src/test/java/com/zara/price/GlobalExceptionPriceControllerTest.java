package com.zara.price;

import com.zara.price.domain.services.PriceService;
import com.zara.price.exception.GlobalExceptionHandler;
import com.zara.price.exception.PriceCalculationException;
import com.zara.price.infraestructure.controller.PriceController;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.mockito.BDDMockito.given;

@WebFluxTest(controllers = {PriceController.class, GlobalExceptionHandler.class})
class GlobalExceptionPriceControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private PriceService priceService;

    @Test
    void testHandleNotFoundException() {
        LocalDateTime testDateTime = LocalDateTime.of(2024, 6, 14, 10, 0);
        given(priceService.calculatePrice(Mockito.any()))
                .willReturn(Mono.error(new PriceCalculationException("Price not found for the given criteria")));

        webTestClient.get()
                .uri("/api/v1/prices/35455/1/{dateTime}", testDateTime.format(DateTimeFormatter.ISO_DATE_TIME))
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testHandleUnauthorizedException() {
        LocalDateTime testDateTime = LocalDateTime.of(2024, 6, 14, 10, 0);
        given(priceService.calculatePrice(Mockito.any()))
                .willReturn(Mono.error(new SecurityException("Unauthorized access")));

        webTestClient.get()
                .uri("/api/v1/prices/35455/1/{dateTime}", testDateTime.format(DateTimeFormatter.ISO_DATE_TIME))
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isUnauthorized()
                .expectBody()
                .jsonPath("$.message").isEqualTo("Unauthorized access")
                .jsonPath("$.error").isEqualTo("SecurityException");
    }

    @Test
    void testHandleForbiddenException() {
        LocalDateTime testDateTime = LocalDateTime.of(2024, 6, 14, 10, 0);
        given(priceService.calculatePrice(Mockito.any()))
                .willReturn(Mono.error(new SecurityException("Forbidden access")));

        webTestClient.get()
                .uri("/api/v1/prices/35455/1/{dateTime}", testDateTime.format(DateTimeFormatter.ISO_DATE_TIME))
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isForbidden()
                .expectBody()
                .jsonPath("$.message").isEqualTo("Forbidden access")
                .jsonPath("$.error").isEqualTo("SecurityException");
    }

    @Test
    void testHandleUnprocessableEntityException() {
        LocalDateTime testDateTime = LocalDateTime.of(2024, 6, 14, 10, 0);
        given(priceService.calculatePrice(Mockito.any()))
                .willReturn(Mono.error(new IllegalArgumentException("Invalid data format"))); // Simula error de datos inválidos

        webTestClient.get()
                .uri("/api/v1/prices/35455/1/{dateTime}", testDateTime.format(DateTimeFormatter.ISO_DATE_TIME))
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody()
                .jsonPath("$.message").isEqualTo("Invalid data format")
                .jsonPath("$.error").isEqualTo("IllegalArgumentException");
    }
}