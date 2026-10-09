package io.tonesniper.domain.ownership;

import jakarta.validation.constraints.NotNull;

public record UpdateOwnershipDto(
    @NotNull String ownershipId,
    @NotNull String ownedBy
) {}
