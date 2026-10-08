package io.tonesniper.domain.gear.ownership;

import jakarta.validation.constraints.NotNull;

import java.time.OffsetDateTime;
import java.util.UUID;

public record NewOwnershipDto(
    @NotNull String gearId,
    @NotNull boolean creatorOwns
) {
    public GearOwnership toEntity(String owner) {
        return new GearOwnership(null,
            UUID.fromString(gearId),
            UUID.fromString(owner),
            UUID.fromString(owner),
            OffsetDateTime.now(),
            UUID.fromString(owner),
            OffsetDateTime.now()
        );
    }
}
