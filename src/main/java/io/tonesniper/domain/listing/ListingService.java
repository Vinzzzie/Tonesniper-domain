package io.tonesniper.domain.listing;

import io.tonesniper.domain.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ListingService {
    private final ListingRepo listingRepo;

    public List<GearListingDto> getListingsByUserId(String userId) {
        return listingRepo.findByCreatedBy(UUID.fromString(userId));
    }

    @Transactional
    public void addGearListing(String userId, NewListingDto newListingDto) {
        this.listingRepo.save(newListingDto.toEntity(userId));
    }

    @Transactional
    public void updateGearListing(String userId, UpdateListingDto updateListingDto) {
        UUID listingId = UUID.fromString(updateListingDto.listingId());
        this.listingRepo.findById(listingId)
            .orElseThrow(NotFoundException::new)
            .update(updateListingDto);
    }
}
