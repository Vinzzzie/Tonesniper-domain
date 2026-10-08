package io.tonesniper.domain.gear.alert;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AlertRepo extends JpaRepository<GearAlert, UUID> {
    @Query("""
        SELECT new io.tonesniper.domain.gear.alert.GearAlertDto(
            ga.id, g, ga.title, ga.minPrice, ga.maxPrice, ga.status)
        FROM GearAlert ga JOIN Gear g ON ga.gearId = g.id
        WHERE ga.createdBy = :createdBy""")
    List<GearAlertDto> findByCreatedBy(UUID createdBy);
}
