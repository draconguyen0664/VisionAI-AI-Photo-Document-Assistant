package com.visionai.document.infrastructure;

import com.visionai.document.application.DocumentRepository;
import com.visionai.document.domain.Document;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("dev")
public final class InMemoryDocumentRepository implements DocumentRepository {
    private final ConcurrentHashMap<UUID, Document> documents = new ConcurrentHashMap<>();
    public Document save(Document document) { documents.put(document.id(), document); return document; }
    public Optional<Document> findById(UUID id) { return Optional.ofNullable(documents.get(id)); }
    public List<Document> findByOwnerId(UUID ownerId) { return documents.values().stream().filter(it -> it.belongsTo(ownerId)).toList(); }
}
