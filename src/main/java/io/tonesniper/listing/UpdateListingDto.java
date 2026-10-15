package io.tonesniper.listing;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record UpdateListingDto(
    @NotNull String listingId,
    @Nullable String title,
    @Nullable BigDecimal askPrice,
    @Nullable GearListing.ListingStatus status
) {}
