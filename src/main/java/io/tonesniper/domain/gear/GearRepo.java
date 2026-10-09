package io.tonesniper.domain.gear;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

@Repository
public interface GearRepo extends JpaRepository<Gear, String> {
    boolean existsByBrandIgnoreCaseAndModelIgnoreCase(String brand, String model);

    Optional<Gear> findGearById(@NotNull UUID id);

    Stream<Gear> findGearByCategory(Gear.Category category);

    @Query("""
        SELECT g FROM Gear g
        WHERE LOWER(g.brand) LIKE LOWER(CONCAT('%', :query, '%'))
           OR LOWER(g.model) LIKE LOWER(CONCAT('%', :query, '%'))
        ORDER BY similarity(CONCAT(g.brand, ' ', g.model), :query) DESC
    """)
    List<Gear> searchLive(@Param("query") String query, Pageable pageable);

    @Query("""
        SELECT g FROM Gear g
        WHERE LOWER(g.brand) LIKE LOWER(CONCAT('%', :query, '%'))
           OR LOWER(g.model) LIKE LOWER(CONCAT('%', :query, '%'))
        ORDER BY similarity(CONCAT(g.brand, ' ', g.model), :query) DESC
    """)
    Stream<Gear> streamLive(@Param("query") String query);


}
