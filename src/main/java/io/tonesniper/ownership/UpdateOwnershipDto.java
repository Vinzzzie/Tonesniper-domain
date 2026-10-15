package io.tonesniper.ownership;

import jakarta.validation.constraints.NotNull;

public record UpdateOwnershipDto(
    @NotNull String ownershipId,
    @NotNull String ownedBy
) {}
