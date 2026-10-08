package io.tonesniper.domain.gear;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@Transactional(readOnly = true)
public class GearService {
    private final GearRepo gearRepo;
    public GearService(GearRepo gearRepo) {
        this.gearRepo = gearRepo;
    }

    public List<GearDto> getAllGear() {
        return gearRepo.findAll().stream().map(GearDto::new).toList();
    }

    public ResponseBodyEmitter liveSearchGear(String query) {
        ResponseBodyEmitter emitter = new ResponseBodyEmitter();
        CompletableFuture.runAsync(() -> {
            try {
                List<GearDto> results = gearRepo
                    .searchLive(query, Pageable.ofSize(10))
                    .stream().map(GearDto::new).toList();
                for (GearDto item : results) {
                    emitter.send(item);
                    Thread.sleep(30); // Simulates streaming delivery
                }
                emitter.complete();
            } catch (Exception e) {
                emitter.completeWithError(e);
            }
        });
        return emitter;
    }

    public SseEmitter liveStreamGear(String query) {
        SseEmitter emitter = new SseEmitter(15000L);
        // Start de database-uitlezing direct op een virtuele thread
        Thread.ofVirtual().name("sse-search-worker").start(() -> {
            // Virtual thread verwerkt de stream per record
            executeLiveStream(query, emitter);
        });
        // Lifecycle hooks voor de emitter
        emitter.onTimeout(emitter::complete);
        emitter.onError(ex -> emitter.complete());
        return emitter;
    }

    private void executeLiveStream(String query, SseEmitter emitter) {
        try (var stream = gearRepo.streamLive(query)) {
            stream.limit(15).forEach(gear -> {
                try {
                    GearDto dto = new GearDto(gear);
                    // Push individueel SSE-event direct naar de client
                    emitter.send(SseEmitter.event()
                        .name("gear-hit")
                        .data(dto));
                    Thread.sleep(20);
                } catch (IOException e) {
                    log.warn("Client connection broken: {}", e.getMessage());
                    throw new RuntimeException("Emitter disconnected", e);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        } catch (Exception ex) {
            emitter.completeWithError(ex);
        } finally {
            emitter.complete();
        }
    }

    public GearDto getGearById(String id) {
        return gearRepo.findGearById(UUID.fromString(id))
            .map(GearDto::new).orElseThrow(RuntimeException::new);
    }

    @Transactional
    public Gear addNewGear(NewGearDto newGearDto) {
        return gearRepo.save(newGearDto.toEntity());
    }

    @Transactional
    public void addAllGear(List<NewGearDto> newGearDtoList) {
        List<Gear> gearList = newGearDtoList.stream().map(NewGearDto::toEntity).toList();
        gearRepo.saveAll(gearList);
    }

}
