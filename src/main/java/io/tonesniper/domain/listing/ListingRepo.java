package io.tonesniper.domain.listing;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ListingRepo extends JpaRepository<GearListing, UUID> {
    @Query("""
        SELECT new io.tonesniper.domain.listing.GearListingDto(
            gl.id, g, gl.title, gl.askPrice, gl.status)
        FROM GearListing gl JOIN Gear g ON gl.gear = g.id
        WHERE gl.createdBy = :createdBy""")
    List<GearListingDto> findByCreatedBy(@NotNull UUID createdBy);

}
