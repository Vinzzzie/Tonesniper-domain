package io.tonesniper.transaction;

record SoldTransactionDto(
    String buyerId,
    String buyerUserName,
    TransactionDto transaction
) {
}
