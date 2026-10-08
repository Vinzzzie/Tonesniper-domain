package io.tonesniper.domain.gear.transaction;

public record UpdateTransactionDto(
    String transactionId,
    GearTransaction.TransactionStatus status
) {
}
