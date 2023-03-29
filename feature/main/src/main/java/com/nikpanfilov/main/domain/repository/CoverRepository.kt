package com.nikpanfilov.main.domain.repository

import com.nikpanfilov.main.domain.entity.Cover

interface CoverRepository {

	suspend fun getCover(): Cover
}