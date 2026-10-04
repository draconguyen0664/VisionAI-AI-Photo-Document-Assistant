package com.visionai.auth;

import java.util.UUID;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public final class DevCurrentUser implements CurrentUser {
    private static final UUID DEMO_USER = UUID.fromString("10000000-0000-0000-0000-000000000001");
    public UUID requireInternalId(Authentication authentication) { return DEMO_USER; }
}
