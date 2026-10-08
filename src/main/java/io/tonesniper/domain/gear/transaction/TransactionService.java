package io.tonesniper.domain.gear.transaction;

import io.tonesniper.domain.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepo transactionRepo;

    public List<GearTransaction> getAllTransactions(String userId, String userType) {
        return switch (userType) {
            case "seller" -> transactionRepo.findAllForSeller(UUID.fromString(userId));
            case "buyer" -> transactionRepo.findAllForBuyer(UUID.fromString(userId));
            default -> transactionRepo.findAllForSellerAndBuyer(UUID.fromString(userId));
        };
    }
    public void createTransaction(String userId, NewTransactionDto transactionDto) {
        transactionRepo.save(transactionDto.toEntity(userId));
    }

    public void updateTransaction(String userId, UpdateTransactionDto transactionDto) {
        UUID transactionId = UUID.fromString(userId);
        this.transactionRepo.findById(transactionId)
            .orElseThrow(NotFoundException::new)
            .update(transactionDto).by(userId);
    }
}
