package com.visionai.presentation.home

import com.visionai.domain.DocumentRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*

class HomePresenter(repository: DocumentRepository, scope: CoroutineScope) {
    val state: StateFlow<HomeState> = combine(repository.observeRecent(6), repository.observeProcessing()) { recent, processing ->
        HomeState.Content("Good morning", recent, processing) as HomeState
    }.catch { emit(HomeState.Error("Could not load your workspace", true)) }
        .stateIn(scope, SharingStarted.WhileSubscribed(5_000), HomeState.Loading)
}
