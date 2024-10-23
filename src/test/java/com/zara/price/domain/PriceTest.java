package com.zara.price.domain;

import com.zara.price.domain.model.Price;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class PriceTest {

    @Test
     void shouldThrowExceptionWhenPriceIsZeroOrNegative() {
        // Arrange
        Price invalidPrice = new Price(1L, 1L, BigDecimal.ZERO, "USD",
                LocalDateTime.now(), LocalDateTime.now().plusDays(1));

        // Act & Assert
        assertThatThrownBy(() -> PriceValidator.validate(invalidPrice))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("El precio debe ser mayor que 0");
    }

    @Test
     void shouldThrowExceptionWhenStartDateIsNull() {
        // Arrange
        Price invalidPrice = new Price(1L, 1L, BigDecimal.TEN, "USD",
                null, LocalDateTime.now().plusDays(1));

        // Act & Assert
        assertThatThrownBy(() -> PriceValidator.validate(invalidPrice))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Las fechas de inicio y fin no pueden ser nulas");
    }

    @Test
     void shouldThrowExceptionWhenEndDateIsNull() {
        // Arrange
        Price invalidPrice = new Price(1L, 1L, BigDecimal.TEN, "USD",
                LocalDateTime.now(), null);

        // Act & Assert
        assertThatThrownBy(() -> PriceValidator.validate(invalidPrice))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Las fechas de inicio y fin no pueden ser nulas");
    }

    @Test
     void shouldThrowExceptionWhenStartDateIsAfterEndDate() {
        // Arrange
        LocalDateTime startDate = LocalDateTime.now().plusDays(2);
        LocalDateTime endDate = LocalDateTime.now().plusDays(1);
        Price invalidPrice = new Price(1L, 1L, BigDecimal.TEN, "USD", startDate, endDate);

        // Act & Assert
        assertThatThrownBy(() -> PriceValidator.validate(invalidPrice))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("La fecha de inicio debe ser anterior a la fecha de fin");
    }

    @Test
     void shouldNotThrowExceptionForValidPrice() {
        // Arrange
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = LocalDateTime.now().plusDays(1);
        Price validPrice = new Price(1L, 1L, BigDecimal.TEN, "USD", startDate, endDate);

        // Act & Assert
        assertDoesNotThrow(() -> PriceValidator.validate(validPrice));
    }
}