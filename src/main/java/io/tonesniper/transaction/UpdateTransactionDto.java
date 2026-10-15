package io.tonesniper.transaction;

public record UpdateTransactionDto(
    String transactionId,
    GearTransaction.TransactionStatus status
) {
}
