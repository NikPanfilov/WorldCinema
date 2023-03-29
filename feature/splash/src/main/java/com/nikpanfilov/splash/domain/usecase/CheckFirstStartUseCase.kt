package com.nikpanfilov.splash.domain.usecase

import com.nikpanfilov.splash.domain.repository.FirstStartRepository

class CheckFirstStartUseCase(private val repository: FirstStartRepository) {

	operator fun invoke() = repository.isFirstStart()
}