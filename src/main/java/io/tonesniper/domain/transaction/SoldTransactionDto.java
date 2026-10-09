package io.tonesniper.domain.transaction;

record SoldTransactionDto(
    String buyerId,
    String buyerUserName,
    TransactionDto transaction
) {
}
