package com.visionai.document.application;

import com.visionai.document.domain.Document;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DocumentRepository {
    Document save(Document document);
    Optional<Document> findById(UUID id);
    List<Document> findByOwnerId(UUID ownerId);
}
