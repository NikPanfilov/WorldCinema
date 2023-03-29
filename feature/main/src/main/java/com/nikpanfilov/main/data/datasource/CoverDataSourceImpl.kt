package com.nikpanfilov.main.data.datasource

import com.nikpanfilov.main.data.api.CoverApi
import com.nikpanfilov.main.data.mapper.toEntity
import com.nikpanfilov.main.domain.entity.Cover

class CoverDataSourceImpl(private val api: CoverApi) : CoverDataSource {

	override suspend fun getCover(): Cover = api.getCover().toEntity()
}