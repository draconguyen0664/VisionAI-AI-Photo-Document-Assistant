package com.visionai.document.domain;

import static org.junit.jupiter.api.Assertions.*;
import java.time.Instant;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class DocumentTest {
    private Document document() { return new Document(UUID.randomUUID(), UUID.randomUUID(), "Contract.pdf", "application/pdf", 2048, Instant.parse("2026-01-01T00:00:00Z")); }
    @Test void acceptsValidProcessingSequence() {
        var value = document();
        value.transitionTo(ProcessingStatus.UPLOADING); value.transitionTo(ProcessingStatus.VALIDATING); value.transitionTo(ProcessingStatus.OCR_PROCESSING); value.transitionTo(ProcessingStatus.AI_ANALYSIS); value.transitionTo(ProcessingStatus.INDEXING); value.transitionTo(ProcessingStatus.READY);
        assertEquals(ProcessingStatus.READY, value.status());
    }
    @Test void rejectsSkippedStages() { assertThrows(InvalidDocumentTransition.class, () -> document().transitionTo(ProcessingStatus.READY)); }
}
