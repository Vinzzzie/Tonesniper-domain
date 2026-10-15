package io.tonesniper.listing;

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
@Table(name = "gear_listings")
public class GearListing {
    @Id
    @NotNull
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull
    @Column(name = "gear_id", nullable = false)
    private UUID gear;

    @Size(max = 200)
    @NotNull
    @Column(name = "title", nullable = false, length = 200)
    private String title;

    @NotNull
    @Column(name = "ask_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal askPrice;

    @NotNull
    @JdbcType(PostgreSQLEnumJdbcType.class)
    @Column(name = "status", nullable = false, length = 20)
    private ListingStatus status;

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

    GearListing(UUID id, UUID gear, String title,
                BigDecimal askPrice, ListingStatus status,
                UUID createdBy, OffsetDateTime createdAt,
                UUID updatedBy, OffsetDateTime updatedAt) {
        this.id = id;
        this.gear = gear;
        this.title = title;
        this.askPrice = askPrice;
        this.status = status;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.updatedBy = updatedBy;
        this.updatedAt = updatedAt;
    }

    void update(UpdateListingDto updateListingDto) {
        if (updateListingDto.title() != null) setTitle(updateListingDto.title());
        if (updateListingDto.askPrice() != null)  setAskPrice(updateListingDto.askPrice());
        if (updateListingDto.status() != null) setStatus(updateListingDto.status());
    }

    enum ListingStatus {
        ACTIVE, RESERVED, SOLD, CANCELLED
    }

}