package com.nikpanfilov.profile.domain.usecase

import com.nikpanfilov.profile.domain.repository.LogOutRepository

class LogOutUseCase(private val repository: LogOutRepository) {

	operator fun invoke() = repository.logOut()
}