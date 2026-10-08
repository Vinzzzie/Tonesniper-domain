package io.tonesniper.domain.user.profile;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserProfileRepo extends JpaRepository<UserProfile, Long> {
    boolean existsByUserName(String userName);
    Optional<UserProfile> findByUserName(String userName);
    Optional<UserProfile> findById(UUID id);

}
