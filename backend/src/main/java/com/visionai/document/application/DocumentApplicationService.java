package com.visionai.document.application;

import com.visionai.document.domain.Document;
import java.time.Clock;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public final class DocumentApplicationService {
    private static final long MAX_SIZE_BYTES = 25L * 1024 * 1024;
    private static final Set<String> ALLOWED_TYPES = Set.of("image/jpeg", "image/png", "image/webp", "application/pdf");
    private final DocumentRepository repository;
    private final Clock clock;

    public DocumentApplicationService(DocumentRepository repository, Clock clock) {
        this.repository = repository;
        this.clock = clock;
    }

    public Document create(UUID authenticatedUserId, CreateDocument command) {
        if (!ALLOWED_TYPES.contains(command.contentType())) throw new IllegalArgumentException("Unsupported file type");
        if (command.sizeBytes() > MAX_SIZE_BYTES) throw new IllegalArgumentException("File exceeds 25 MB");
        var document = new Document(UUID.randomUUID(), authenticatedUserId, command.name(), command.contentType(), command.sizeBytes(), clock.instant());
        return repository.save(document);
    }

    public Document get(UUID authenticatedUserId, UUID documentId) {
        var document = repository.findById(documentId).orElseThrow(() -> new DocumentNotFound(documentId));
        if (!document.belongsTo(authenticatedUserId)) throw new DocumentAccessDenied();
        return document;
    }

    public List<Document> list(UUID authenticatedUserId) { return repository.findByOwnerId(authenticatedUserId); }

    public record CreateDocument(String name, String contentType, long sizeBytes) {}
}
