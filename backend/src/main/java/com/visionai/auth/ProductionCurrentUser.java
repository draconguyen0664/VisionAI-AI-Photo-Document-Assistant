package com.visionai.auth;

import java.nio.charset.StandardCharsets;
import java.util.UUID;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
@Profile("!dev")
public final class ProductionCurrentUser implements CurrentUser {
    public UUID requireInternalId(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated() || authentication.getName() == null) throw new IllegalStateException("Authenticated principal is required");
        return UUID.nameUUIDFromBytes(("firebase:" + authentication.getName()).getBytes(StandardCharsets.UTF_8));
    }
}
