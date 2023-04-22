package com.nikpanfilov.main.domain.usecase

import com.nikpanfilov.main.domain.entity.EpisodeView
import com.nikpanfilov.main.domain.repository.HistoryRepository

class GetHistoryUseCase(private val repository: HistoryRepository) {

	suspend operator fun invoke(): List<EpisodeView> = repository.getHistory()
}