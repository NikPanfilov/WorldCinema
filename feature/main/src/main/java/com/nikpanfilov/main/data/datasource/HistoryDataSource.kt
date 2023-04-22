package com.nikpanfilov.main.data.datasource

import com.nikpanfilov.main.domain.entity.EpisodeView

interface HistoryDataSource {
	suspend fun getHistory(): List<EpisodeView>
}