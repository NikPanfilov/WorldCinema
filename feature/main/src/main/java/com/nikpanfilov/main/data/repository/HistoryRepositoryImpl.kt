package com.nikpanfilov.main.data.repository

import com.nikpanfilov.main.data.datasource.HistoryDataSource
import com.nikpanfilov.main.domain.entity.EpisodeView
import com.nikpanfilov.main.domain.repository.HistoryRepository

class HistoryRepositoryImpl(private val dataSource: HistoryDataSource) : HistoryRepository {

	override suspend fun getHistory(): List<EpisodeView> = dataSource.getHistory()
}