package io.tonesniper.gear;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.List;

public record NewGearDto(
    String brand,
    String model,
    Gear.Category category,
    List<KnobSpec> knobs
) implements Serializable {
    Gear toEntity() {
        return  new Gear(null, //INSERT JPA ENTITY
            this.brand, this.model, this.category, this.knobs,
            OffsetDateTime.now(), OffsetDateTime.now()
        );
    }
}
