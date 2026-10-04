package com.visionai.auth;

import java.util.UUID;
import org.springframework.security.core.Authentication;

public interface CurrentUser {
    UUID requireInternalId(Authentication authentication);
}
