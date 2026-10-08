package io.tonesniper.domain.gear.transaction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.type.PostgreSQLEnumJdbcType;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "gear_transactions")
public class GearTransaction {
    @Id
    @NotNull
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull
    @Column(name = "gear_id", nullable = false)
    private UUID gearId;

    @NotNull
    @Column(name = "seller_id", nullable = false)
    private UUID sellerId;

    @NotNull
    @Column(name = "gear_listing_id", nullable = false)
    private UUID gearListingId;

    @NotNull
    @Column(name = "buyer_id", nullable = false)
    private UUID buyerId;

    @Column(name = "gear_alert_id")
    private UUID gearAlertId;

    @NotNull
    @Column(name = "agreed_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal agreedPrice;

    @Size(max = 20)
    @NotNull
    @JdbcType(PostgreSQLEnumJdbcType.class)
    @Column(name = "status", nullable = false, length = 20)
    private TransactionStatus status;

    @NotNull
    @Column(name = "created_by", nullable = false)
    private UUID createdBy;

    @NotNull
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @NotNull
    @Column(name = "updated_by", nullable = false)
    private UUID updatedBy;

    @NotNull
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt;

    GearTransaction(UUID id, UUID gearId,
                    UUID sellerId, UUID gearListingId,
                    UUID buyerId, UUID gearAlertId,
                    BigDecimal agreedPrice, TransactionStatus status,
                    UUID createdBy, OffsetDateTime createdAt,
                    UUID updatedBy, OffsetDateTime updatedAt) {
        this.id = id;
        this.gearId = gearId;
        this.sellerId = sellerId;
        this.gearListingId = gearListingId;
        this.buyerId = buyerId;
        this.gearAlertId = gearAlertId;
        this.agreedPrice = agreedPrice;
        this.status = status;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.updatedBy = updatedBy;
        this.updatedAt = updatedAt;
    }

    GearTransaction update(UpdateTransactionDto dto) {
        if (dto.status()!=getStatus()) setStatus(dto.status());
        return this;
    }

    void by(String userId) {
        setUpdatedBy(UUID.fromString(userId));
        setUpdatedAt(OffsetDateTime.now());
    }

    enum TransactionStatus {
        INITIATED, PAYED, SENT, ARRIVED
    }

    enum USERTYPE {
        BUYER, SELLER, ALL
    }

}