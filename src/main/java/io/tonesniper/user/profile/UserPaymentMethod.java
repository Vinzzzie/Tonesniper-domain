package io.tonesniper.user.profile;

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
import org.hibernate.annotations.JdbcType;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.dialect.type.PostgreSQLEnumJdbcType;
import org.hibernate.type.SqlTypes;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "user_payment_methods")
public class UserPaymentMethod {
    @Id
    @NotNull
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull
    @Column(name = "user_profile_id", nullable = false)
    private UUID userProfileId;

    @Size(max = 30)
    @NotNull
    @Column(name = "provider", nullable = false, length = 30)
    private String provider;

    @Size(max = 30)
    @NotNull
    @JdbcType(PostgreSQLEnumJdbcType.class)
    @Column(name = "payment_type", nullable = false, length = 30)
    private PaymentType paymentType;

    @Size(max = 100)
    @NotNull
    @Column(name = "provider_payment_method_id", nullable = false, length = 100)
    private String providerPaymentMethodId;

    @Size(max = 4)
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "last_four_digits", length = 4)
    private String lastFourDigits;

    @Size(max = 30)
    @Column(name = "card_brand", length = 30)
    private String cardBrand;

    @Column(name = "expiry_month")
    private Short expiryMonth;

    @Column(name = "expiry_year")
    private Short expiryYear;

    @NotNull
    @ColumnDefault("false")
    @Column(name = "is_default", nullable = false)
    private Boolean isDefault;

    @NotNull
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @NotNull
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt;

    enum PaymentProvider {
        STRIPE, MOLLIE, PAYPAL
    }
    
    enum PaymentType {
        CREDIT_CARD,
        SEPA_DEBIT,
        PAYPAL_ACCOUNT
    }
}