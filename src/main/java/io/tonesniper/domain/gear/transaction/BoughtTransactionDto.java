package io.tonesniper.domain.gear.transaction;

record BoughtTransactionDto(
    String sellerId,
    String sellerUserName,
    TransactionDto transaction
) {
}
