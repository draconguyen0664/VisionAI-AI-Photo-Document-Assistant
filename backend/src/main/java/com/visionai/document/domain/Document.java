package com.visionai.document.domain;

import java.time.Instant;
import java.util.EnumSet;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public final class Document {
    private static final Map<ProcessingStatus, EnumSet<ProcessingStatus>> TRANSITIONS = Map.of(
        ProcessingStatus.QUEUED, EnumSet.of(ProcessingStatus.UPLOADING, ProcessingStatus.FAILED),
        ProcessingStatus.UPLOADING, EnumSet.of(ProcessingStatus.VALIDATING, ProcessingStatus.FAILED),
        ProcessingStatus.VALIDATING, EnumSet.of(ProcessingStatus.IMAGE_PROCESSING, ProcessingStatus.OCR_PROCESSING, ProcessingStatus.FAILED),
        ProcessingStatus.IMAGE_PROCESSING, EnumSet.of(ProcessingStatus.OCR_PROCESSING, ProcessingStatus.FAILED),
        ProcessingStatus.OCR_PROCESSING, EnumSet.of(ProcessingStatus.AI_ANALYSIS, ProcessingStatus.INDEXING, ProcessingStatus.FAILED),
        ProcessingStatus.AI_ANALYSIS, EnumSet.of(ProcessingStatus.INDEXING, ProcessingStatus.FAILED),
        ProcessingStatus.INDEXING, EnumSet.of(ProcessingStatus.READY, ProcessingStatus.FAILED),
        ProcessingStatus.READY, EnumSet.noneOf(ProcessingStatus.class),
        ProcessingStatus.FAILED, EnumSet.of(ProcessingStatus.QUEUED)
    );

    private final UUID id;
    private final UUID ownerId;
    private final String name;
    private final String contentType;
    private final long sizeBytes;
    private final Instant createdAt;
    private ProcessingStatus status;
    private DocumentType type;

    public Document(UUID id, UUID ownerId, String name, String contentType, long sizeBytes, Instant createdAt) {
        this.id = Objects.requireNonNull(id);
        this.ownerId = Objects.requireNonNull(ownerId);
        this.name = requireText(name, "name");
        this.contentType = requireText(contentType, "contentType");
        if (sizeBytes <= 0) throw new IllegalArgumentException("sizeBytes must be positive");
        this.sizeBytes = sizeBytes;
        this.createdAt = Objects.requireNonNull(createdAt);
        this.status = ProcessingStatus.QUEUED;
        this.type = DocumentType.OTHER;
    }

    public void transitionTo(ProcessingStatus next) {
        Objects.requireNonNull(next);
        if (!TRANSITIONS.get(status).contains(next)) {
            throw new InvalidDocumentTransition(status, next);
        }
        status = next;
    }

    public void classifyAs(DocumentType type) { this.type = Objects.requireNonNull(type); }
    public boolean belongsTo(UUID userId) { return ownerId.equals(userId); }
    public UUID id() { return id; }
    public UUID ownerId() { return ownerId; }
    public String name() { return name; }
    public String contentType() { return contentType; }
    public long sizeBytes() { return sizeBytes; }
    public Instant createdAt() { return createdAt; }
    public ProcessingStatus status() { return status; }
    public DocumentType type() { return type; }

    private static String requireText(String value, String field) {
        if (value == null || value.isBlank()) throw new IllegalArgumentException(field + " is required");
        return value.trim();
    }
}
