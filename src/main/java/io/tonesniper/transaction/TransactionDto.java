package io.tonesniper.transaction;

import io.tonesniper.gear.Gear;
import io.tonesniper.gear.GearDto;
import io.tonesniper.user.profile.UserProfile;

import java.math.BigDecimal;
import java.util.UUID;

record TransactionDto(
    String id,
    GearDto gear,
    UserProfile seller,
    UUID alertId,
    UserProfile buyer,
    UUID listingId,
    BigDecimal agreedPrice,
    GearTransaction.TransactionStatus status
) {
    TransactionDto(UUID id, Gear gear,
                   UserProfile seller, UUID alert,
                   UserProfile buyer, UUID listing,
                   BigDecimal agreedPrice, GearTransaction.TransactionStatus status) {
        this(id.toString(), new GearDto(gear), seller, alert, buyer, listing, agreedPrice, status);
    }
}
