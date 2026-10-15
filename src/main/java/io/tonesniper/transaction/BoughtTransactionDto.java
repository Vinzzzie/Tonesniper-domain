package io.tonesniper.transaction;

record BoughtTransactionDto(
    String sellerId,
    String sellerUserName,
    TransactionDto transaction
) {
}
