package com.visionai.domain

import kotlinx.serialization.Serializable

@Serializable
data class Document(
    val id: String,
    val name: String,
    val type: DocumentType,
    val status: ProcessingStatus,
    val thumbnailUrl: String? = null,
    val createdAt: String,
    val favorite: Boolean = false,
)

@Serializable enum class DocumentType { INVOICE, RECEIPT, CONTRACT, RESUME, BUSINESS_CARD, REPORT, ARTICLE, LETTER, FORM, NOTES, SCREENSHOT, OTHER }
@Serializable enum class ProcessingStatus { QUEUED, UPLOADING, VALIDATING, IMAGE_PROCESSING, OCR_PROCESSING, AI_ANALYSIS, INDEXING, READY, FAILED }
