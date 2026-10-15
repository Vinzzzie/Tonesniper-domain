package io.tonesniper.alert;

import io.tonesniper.gear.Gear;
import io.tonesniper.gear.GearDto;

import java.math.BigDecimal;
import java.util.UUID;

public record GearAlertDto(
    String id,
    GearDto gear,
    String title,
    BigDecimal minPrice,
    BigDecimal maxPrice,
    GearAlert.AlertStatus status
) {
    public GearAlertDto(UUID id, Gear gear, String title,
                        BigDecimal minPrice, BigDecimal maxPrice,
                        GearAlert.AlertStatus status) {
        this(id.toString(), new GearDto(gear), title, minPrice, maxPrice, status);
    }

}
