package com.zara.price.infraestructure;

import com.zara.price.domain.PriceSearchCriteria;
import com.zara.price.domain.model.Price;
import com.zara.price.domain.services.PriceService;
import com.zara.price.infraestructure.controller.PriceController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.mockito.BDDMockito.given;

@WebFluxTest(PriceController.class)
class PriceControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private PriceService priceService;

    private Price createTestPrice(BigDecimal price) {
        Price testPrice = new Price();
        testPrice.setId(1L);
        testPrice.setBrandId(1L);
        testPrice.setProductId(35455L);
        testPrice.setPriceList(1);
        testPrice.setStartDate(LocalDateTime.now());
        testPrice.setEndDate(LocalDateTime.now());
        testPrice.setPrices(price);
        testPrice.setCurr("EUR");
        return testPrice;
    }


    @Test
    void testGetPriceAt1000OnJune14() {
        LocalDateTime testDateTime = LocalDateTime.of(2024, 6, 14, 10, 0);
        given(priceService.calculatePrice(new PriceSearchCriteria(35455L, 1L, testDateTime, testDateTime)))
                .willReturn(Mono.just(createTestPrice(new BigDecimal("35.50"))));

        webTestClient.get()
                .uri("/api/v1/prices/35455/1/{dateTime}", testDateTime.format(DateTimeFormatter.ISO_DATE_TIME))
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.price").isEqualTo(35.50);
    }

    @Test
    void testGetPriceAt1600OnJune14() {
        LocalDateTime testDateTime = LocalDateTime.of(2024, 6, 14, 16, 0);
        given(priceService.calculatePrice(new PriceSearchCriteria(35455L, 1L, testDateTime, testDateTime)))
                .willReturn(Mono.just(createTestPrice(new BigDecimal("25.45"))));

        webTestClient.get()
                .uri("/api/v1/prices/35455/1/{dateTime}", testDateTime.format(DateTimeFormatter.ISO_DATE_TIME))
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.price").isEqualTo(25.45);
    }

    @Test
    void testGetPriceAt1000OnJune15() {
        LocalDateTime testDateTime = LocalDateTime.of(2024, 6, 15, 10, 0);
        given(priceService.calculatePrice(new PriceSearchCriteria(35455L, 1L, testDateTime, testDateTime)))
                .willReturn(Mono.just(createTestPrice(new BigDecimal("30.50"))));

        webTestClient.get()
                .uri("/api/v1/prices/35455/1/{dateTime}", testDateTime.format(DateTimeFormatter.ISO_DATE_TIME))
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.price").isEqualTo(30.50);
    }

    @Test
    void testGetPriceAt2100OnJune16() {
        LocalDateTime testDateTime = LocalDateTime.of(2024, 6, 16, 21, 0);
        given(priceService.calculatePrice(new PriceSearchCriteria(35455L, 1L, testDateTime, testDateTime)))
                .willReturn(Mono.just(createTestPrice(new BigDecimal("38.95"))));

        webTestClient.get()
                .uri("/api/v1/prices/35455/1/{dateTime}", testDateTime.format(DateTimeFormatter.ISO_DATE_TIME))
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.price").isEqualTo(38.95);
    }

    @Test
    void testGetPriceAt2100OnJune14() {
        LocalDateTime testDateTime = LocalDateTime.of(2024, 6, 14, 21, 0);
        given(priceService.calculatePrice(new PriceSearchCriteria(35455L, 1L, testDateTime, testDateTime)))
                .willReturn(Mono.just(createTestPrice(new BigDecimal("25.45"))));

        webTestClient.get()
                .uri("/api/v1/prices/35455/1/{dateTime}", testDateTime.format(DateTimeFormatter.ISO_DATE_TIME))
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.price").isEqualTo(25.45);
    }
}