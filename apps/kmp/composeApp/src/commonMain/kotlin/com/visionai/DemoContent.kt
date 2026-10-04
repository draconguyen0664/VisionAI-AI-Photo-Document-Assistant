package com.visionai

import com.visionai.domain.*

object DemoContent {
    val documents = listOf(
        Document("1", "Product strategy.pdf", DocumentType.REPORT, ProcessingStatus.READY, createdAt = "2026-10-04T08:00:00Z"),
        Document("2", "Travel receipt.jpg", DocumentType.RECEIPT, ProcessingStatus.READY, createdAt = "2026-10-03T10:00:00Z"),
        Document("3", "Service agreement.pdf", DocumentType.CONTRACT, ProcessingStatus.READY, createdAt = "2026-10-01T12:00:00Z"),
    )
    val processing = listOf(Document("4", "Research notes.pdf", DocumentType.NOTES, ProcessingStatus.AI_ANALYSIS, createdAt = "2026-10-04T09:00:00Z"))
}
