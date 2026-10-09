package io.tonesniper.domain.transaction;

record BoughtTransactionDto(
    String sellerId,
    String sellerUserName,
    TransactionDto transaction
) {
}
