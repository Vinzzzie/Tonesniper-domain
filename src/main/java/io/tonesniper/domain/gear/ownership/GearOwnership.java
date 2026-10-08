package io.tonesniper.domain.gear.ownership;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "gear_ownership")
class GearOwnership {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull
    @Column(name = "gear_id", nullable = false)
    private UUID gearId;

    @Column(name = "owned_by", nullable = false)
    private UUID ownedBy;

    @NotNull
    @Column(name = "created_by", nullable = false)
    private UUID createdBy;

    @NotNull
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @Column(name = "updated_by", nullable = false)
    private UUID updatedBy;

    @NotNull
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt;

    GearOwnership(UUID id, UUID gearId, UUID ownedBy,
                  UUID createdBy, OffsetDateTime createdAt,
                  UUID updatedBy, OffsetDateTime updatedAt) {
        this.id = id;
        this.gearId = gearId;
        this.ownedBy = ownedBy;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.updatedBy = updatedBy;
        this.updatedAt = updatedAt;
    }

    GearOwnership update(UpdateOwnershipDto updateOwnershipDto) {
        if (updateOwnershipDto.ownershipId() != null) setOwnedBy(UUID.fromString(updateOwnershipDto.ownedBy()));
        return this;
    }

    GearOwnership by(String userId) {
        setUpdatedBy(UUID.fromString(userId));
        setUpdatedAt(OffsetDateTime.now());
        return this;
    }

}