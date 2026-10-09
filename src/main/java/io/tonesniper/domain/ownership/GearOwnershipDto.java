package io.tonesniper.domain.ownership;

import io.tonesniper.domain.gear.Gear;

import java.util.UUID;

public record GearOwnershipDto(
    String id,
    String brand,
    String model,
    Gear.Category category
) {
    public GearOwnershipDto(UUID id, String brand, String model,
                            Gear.Category category) {
        this(id.toString(), brand, model, category);
    }
}
