package com.visionai.domain

import kotlinx.coroutines.flow.Flow

interface DocumentRepository {
    fun observeRecent(limit: Int): Flow<List<Document>>
    fun observeProcessing(): Flow<List<Document>>
    suspend fun retry(documentId: String)
}
