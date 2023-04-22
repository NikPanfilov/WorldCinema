package com.nikpanfilov.main.data.datasource

import com.nikpanfilov.main.data.api.HistoryApi
import com.nikpanfilov.main.data.mapper.toEntity
import com.nikpanfilov.main.domain.entity.EpisodeView

class HistoryDataSourceImpl(private val api: HistoryApi) : HistoryDataSource {

	override suspend fun getHistory(): List<EpisodeView> = api.getHistory().map { it.toEntity() }
}