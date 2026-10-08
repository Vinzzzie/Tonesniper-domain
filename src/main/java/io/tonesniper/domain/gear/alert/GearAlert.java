package io.tonesniper.domain.gear.alert;

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
@Table(name = "gear_alerts")
public class GearAlert {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull
    @Column(name = "gear_id", nullable = false)
    private UUID gearId;

    @Size(max = 150)
    @NotNull
    @Column(name = "title", nullable = false, length = 150)
    private String title;

    @NotNull
    @JdbcType(PostgreSQLEnumJdbcType.class)
    @Column(name = "status", nullable = false, length = 20)
    private AlertStatus status;

    @NotNull
    @Column(name = "min_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal minPrice;

    @NotNull
    @Column(name = "max_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal maxPrice;

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

//    @ManyToOne(fetch = FetchType.LAZY, optional = true)
//    @JoinColumn(name = "gear_id", insertable = false, updatable = false)
//    private Gear gear;

    GearAlert(UUID id, UUID gearId, String title, AlertStatus status,
              BigDecimal minPrice, BigDecimal maxPrice,
              UUID createdBy, OffsetDateTime createdAt,
              UUID updatedBy, OffsetDateTime updatedAt) {
        this.id = id;
        this.gearId = gearId;
        this.title = title;
        this.status = status;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.updatedBy = updatedBy;
        this.updatedAt = updatedAt;
    }

    GearAlert update(UpdateAlertDto updateAlertDto) {
        if (updateAlertDto.title()!=null && !updateAlertDto.title().isEmpty())
            setTitle(updateAlertDto.title());
        if (updateAlertDto.minPrice()!=null)
            setMinPrice(updateAlertDto.minPrice());
        if (updateAlertDto.maxPrice()!=null)
            setMaxPrice(updateAlertDto.maxPrice());
        if (updateAlertDto.status()!=null)
            setStatus(updateAlertDto.status());
        return this;
    }

    void by(String userId) {
        setUpdatedBy(UUID.fromString(userId));
        setUpdatedAt(OffsetDateTime.now());
    }

    enum AlertStatus {
        ACTIVE, FOUND, SOLD, CANCELLED
    }
}