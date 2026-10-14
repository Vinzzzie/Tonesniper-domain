package io.tonesniper.domain.gear;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.List;

public record GearInfoDto (
    String brand,
    String model,
    Gear.Category category,
    List<KnobSpec> knobs,
    String info
) implements Serializable {
    public Gear toEntity() {
        return  new Gear(null, //INSERT JPA ENTITY
            this.brand, this.model, this.category, this.knobs,
            OffsetDateTime.now(), OffsetDateTime.now()
        );
    }
    public boolean hasNoNullValue() {
        return !(brand == null || model == null || category == null || knobs == null);
    }
    public boolean notExistsInRepo(GearRepo gearRepo) {
        return !gearRepo.existsByBrandIgnoreCaseAndModelIgnoreCase(brand, model);
    }
}
