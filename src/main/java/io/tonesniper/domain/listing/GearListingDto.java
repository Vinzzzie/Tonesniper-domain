package io.tonesniper.domain.listing;

import io.tonesniper.domain.gear.Gear;
import io.tonesniper.domain.gear.GearDto;

import java.math.BigDecimal;
import java.util.UUID;

public record GearListingDto(
    String id,
    GearDto gear,
    String title,
    BigDecimal askPrice,
    GearListing.ListingStatus status
) {
    GearListingDto(UUID listingId, Gear gear, String title,
                   BigDecimal askPrice, GearListing.ListingStatus status) {
        this(listingId.toString(), new GearDto(gear), title, askPrice, status);
    }
}
