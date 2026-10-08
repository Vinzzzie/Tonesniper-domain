package io.tonesniper.domain.gear.transaction;

record SoldTransactionDto(
    String buyerId,
    String buyerUserName,
    TransactionDto transaction
) {
}
