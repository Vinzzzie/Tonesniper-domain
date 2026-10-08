package io.tonesniper.domain.gear.ownership;

import jakarta.validation.constraints.NotNull;

public record UpdateOwnershipDto(
    @NotNull String ownershipId,
    @NotNull String ownedBy
) {}
