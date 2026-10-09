package io.tonesniper.domain.transaction;

public record UpdateTransactionDto(
    String transactionId,
    GearTransaction.TransactionStatus status
) {
}
