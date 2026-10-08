package io.tonesniper.domain.gear.ownership;

import io.tonesniper.domain.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OwnershipService {
    private final OwnershipRepo ownershipRepo;

    public List<GearOwnershipDto> getGearOwnedByUserId(String userId) {
        return ownershipRepo.findGearOwnedByUserId(UUID.fromString(userId));
    }

    @Transactional
    public void addGearOwnership(String userId, NewOwnershipDto newOwnershipDto) {
        this.ownershipRepo.save(newOwnershipDto.toEntity(userId));
    }

    @Transactional
    public void changeGearOwnership(String userId, UpdateOwnershipDto updateOwnershipDto) {
        UUID ownershipId = UUID.fromString(updateOwnershipDto.ownershipId());
        this.ownershipRepo.findById(ownershipId)
            .orElseThrow(NotFoundException::new)
            .update(updateOwnershipDto).by(userId);
    }
}
