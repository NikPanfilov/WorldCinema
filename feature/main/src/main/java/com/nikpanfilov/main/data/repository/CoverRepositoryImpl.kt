package com.nikpanfilov.main.data.repository

import com.nikpanfilov.main.data.datasource.CoverDataSource
import com.nikpanfilov.main.domain.entity.Cover
import com.nikpanfilov.main.domain.repository.CoverRepository

class CoverRepositoryImpl(private val dataSource: CoverDataSource):CoverRepository {

	override suspend fun getCover(): Cover =dataSource.getCover()
}