package io.tonesniper.listing;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

public record NewListingDto(
    @NotNull String gearId,
    @NotNull String title,
    @NotNull BigDecimal askPrice,
    @NotNull GearListing.ListingStatus status
) {
    public GearListing toEntity(String createdBy) {
        return new GearListing(null,
            UUID.fromString(gearId),
            title.isEmpty()?"%s for sale!".formatted(gearId) : title,
            askPrice,
            GearListing.ListingStatus.ACTIVE,
            UUID.fromString(createdBy),
            OffsetDateTime.now(),
            UUID.fromString(createdBy),
            OffsetDateTime.now()
        );
    }
}
