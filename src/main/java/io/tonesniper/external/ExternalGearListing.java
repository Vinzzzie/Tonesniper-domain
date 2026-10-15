package io.tonesniper.external;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "external_gear_listings")
public class ExternalGearListing {
    @Id
    @NotNull
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull
    @Column(name = "shop_id", nullable = false)
    private UUID shopId;

    @NotNull
    @Column(name = "gear_id", nullable = false)
    private UUID gearId;

    @Size(max = 500)
    @NotNull
    @Column(name = "url", nullable = false, length = 500)
    private String url;

    @Size(max = 100)
    @Column(name = "product_id", length = 100)
    private String productId;

    @NotNull
    @ColumnDefault("true")
    @Column(name = "in_stock", nullable = false)
    private Boolean inStock;

    @NotNull
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "last_scraped_at", nullable = false)
    private OffsetDateTime lastScrapedAt;


}