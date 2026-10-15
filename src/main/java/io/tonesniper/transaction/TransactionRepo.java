package io.tonesniper.transaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TransactionRepo extends JpaRepository<GearTransaction, UUID> {

    @Query("""
        FROM GearTransaction gt WHERE :userId
        IN (gt.buyerId, gt.sellerId)
        """)
    List<GearTransaction> findAllForSellerAndBuyer(UUID userId);

    @Query("FROM GearTransaction WHERE sellerId = :userId")
    List<GearTransaction> findAllForSeller(UUID userId);

    @Query("FROM GearTransaction WHERE buyerId = :userId")
    List<GearTransaction> findAllForBuyer(UUID userId);
}
