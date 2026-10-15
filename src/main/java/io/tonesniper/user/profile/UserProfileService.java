package io.tonesniper.user.profile;

import org.springframework.stereotype.Service;

@Service
public class UserProfileService {
    private final UserProfileRepo userProfileRepo;
    public UserProfileService(UserProfileRepo userProfileRepo) {
        this.userProfileRepo = userProfileRepo;
    }

}
