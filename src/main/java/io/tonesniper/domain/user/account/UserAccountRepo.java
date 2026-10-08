package io.tonesniper.domain.user.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserAccountRepo extends JpaRepository<UserAccount, Long> {
    Optional<UserAccount> findById(UUID id);
    Optional<UserAccount> findByEmailAddress(String emailAddress);

    boolean existsByEmailAddress(String emailAddress);
}
