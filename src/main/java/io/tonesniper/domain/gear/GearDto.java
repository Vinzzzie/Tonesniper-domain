package io.tonesniper.domain.gear;

import java.io.Serializable;
import java.util.LinkedHashSet;

public record GearDto(
    String id,
    String brand,
    String model,
    Gear.Category category,
    LinkedHashSet<KnobSpec> knobs
) implements Serializable {

    public GearDto(Gear gear) {
        this(
            gear.getId().toString(),
            gear.getBrand(),
            gear.getModel(),
            gear.getCategory(),
            new LinkedHashSet<>(gear.getKnobs())
        );
    }
}
