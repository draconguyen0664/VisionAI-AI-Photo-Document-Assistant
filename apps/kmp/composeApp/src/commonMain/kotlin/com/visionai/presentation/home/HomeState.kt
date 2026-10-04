package com.visionai.presentation.home

import com.visionai.domain.Document

sealed interface HomeState {
    data object Loading : HomeState
    data class Content(val greeting: String, val recent: List<Document>, val processing: List<Document>) : HomeState
    data class Error(val message: String, val retryable: Boolean) : HomeState
}

enum class QuickTool(val label: String, val subtitle: String) {
    AI_SCAN("AI Scan", "Smart capture"), OCR("OCR", "Extract text"), ASK_AI("Ask AI", "Find answers"), TRANSLATE("Translate", "40+ languages")
}
