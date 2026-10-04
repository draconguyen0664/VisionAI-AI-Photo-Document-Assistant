package com.visionai.presentation.home

import com.visionai.domain.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertIs

class HomePresenterTest {
    @Test fun emitsContentFromRepository() = runTest {
        val repository = object : DocumentRepository {
            override fun observeRecent(limit: Int) = flowOf(emptyList<Document>())
            override fun observeProcessing() = flowOf(emptyList<Document>())
            override suspend fun retry(documentId: String) = Unit
        }
        val presenter = HomePresenter(repository, backgroundScope)
        assertIs<HomeState.Content>(presenter.state.filterNot { it is HomeState.Loading }.first())
    }
}
