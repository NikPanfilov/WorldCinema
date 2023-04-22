package com.nikpanfilov.main.domain.repository

import com.nikpanfilov.main.domain.entity.EpisodeView

interface HistoryRepository {

	suspend fun getHistory(): List<EpisodeView>
}