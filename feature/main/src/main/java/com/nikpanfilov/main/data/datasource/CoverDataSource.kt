package com.nikpanfilov.main.data.datasource

import com.nikpanfilov.main.domain.entity.Cover

interface CoverDataSource {

	suspend fun getCover(): Cover
}