package com.nikpanfilov.main.domain.usecase

import com.nikpanfilov.main.domain.entity.Cover
import com.nikpanfilov.main.domain.repository.CoverRepository

class GetCoverUseCase(private val repository: CoverRepository) {

	suspend operator fun invoke(): Cover = repository.getCover()
}