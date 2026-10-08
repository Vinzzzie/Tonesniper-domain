package io.tonesniper.domain.gear.ownership;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OwnershipRepo extends JpaRepository<GearOwnership, UUID> {
    @Query("""
    SELECT new io.tonesniper.domain.gear.ownership.GearOwnershipDto(
        go.id, g.brand, g.model, g.category) FROM GearOwnership go
    JOIN Gear g ON go.gearId = g.id
    JOIN UserProfile up ON go.ownedBy = up.id
    WHERE go.ownedBy = :ownedBy
    """)
    List<GearOwnershipDto> findGearOwnedByUserId(@NotNull UUID ownedBy);

}
