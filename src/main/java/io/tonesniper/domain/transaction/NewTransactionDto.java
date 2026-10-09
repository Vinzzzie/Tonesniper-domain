package io.tonesniper.domain.transaction;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

public record NewTransactionDto(
    @NotNull String gearId,
    @NotNull String sellerId,
    @NotNull String listingId,
    @NotNull String buyerId,
    @Nullable String alertId,
    @NotNull BigDecimal agreedPrice,
    @NotNull GearTransaction.TransactionStatus status
) {
    public GearTransaction toEntity(String userId) {
        return new GearTransaction(null,
            UUID.fromString(gearId),
            UUID.fromString(sellerId),
            UUID.fromString(listingId),
            UUID.fromString(buyerId),
            alertId==null?null:UUID.fromString(alertId),
            agreedPrice,
            status,
            UUID.fromString(userId),
            OffsetDateTime.now(),
            UUID.fromString(userId),
            OffsetDateTime.now()
        );
    }
}
