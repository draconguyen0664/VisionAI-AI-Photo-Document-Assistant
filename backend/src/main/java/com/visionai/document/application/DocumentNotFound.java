package com.visionai.document.application;

import java.util.UUID;

public final class DocumentNotFound extends RuntimeException {
    public DocumentNotFound(UUID id) { super("Document was not found: " + id); }
}
