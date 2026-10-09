package io.tonesniper.domain.alert;

import io.tonesniper.domain.NotFoundException;
import io.tonesniper.domain.NotInsertedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AlertService {
    private final AlertRepo alertRepo;

    public List<GearAlertDto> getAlertsByUserId(String userId) {
        return alertRepo.findByCreatedBy(UUID.fromString(userId));
    }

    @Transactional
    public void createAlert(String userId, NewAlertDto newAlertDto) {
        try {
            this.alertRepo.saveAndFlush(newAlertDto.createdBy(userId));
        } catch (Exception e) {
            throw new NotInsertedException("Alert not created", e);
        }
    }

    @Transactional
    public void updateAlert(String userId, UpdateAlertDto updateAlertDto) {
        UUID alertId = UUID.fromString(updateAlertDto.alertId());
        this.alertRepo.findById(alertId)
            .orElseThrow(NotFoundException::new)
            .update(updateAlertDto).by(userId);
    }
}
