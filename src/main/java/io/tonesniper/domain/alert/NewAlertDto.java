package io.tonesniper.domain.alert;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

public record NewAlertDto(
    @NotNull String gearId,
    @NotNull String title,
    @Min(0) BigDecimal minPrice,
    @Max(9999999) BigDecimal maxPrice,
    @NotNull GearAlert.AlertStatus status
) {
    public GearAlert createdBy(String userId) {
        return new GearAlert(null,
            UUID.fromString(gearId),
            title.isEmpty() ? "%s - Alert".formatted(gearId) : title,
            status,
            minPrice,
            maxPrice,
            UUID.fromString(userId),
            OffsetDateTime.now(),
            UUID.fromString(userId),
            OffsetDateTime.now()
        );
    }
}
