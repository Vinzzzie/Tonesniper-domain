package io.tonesniper.alert;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record UpdateAlertDto(
    @NotNull String alertId,
    @Nullable String title,
    @Nullable BigDecimal minPrice,
    @Nullable BigDecimal maxPrice,
    @Nullable GearAlert.AlertStatus status
) {
}
