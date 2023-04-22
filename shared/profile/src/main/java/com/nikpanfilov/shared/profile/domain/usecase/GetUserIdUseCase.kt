package com.nikpanfilov.shared.profile.domain.usecase

import com.nikpanfilov.shared.profile.domain.repository.UserIdRepository

class GetUserIdUseCase(private val repository: UserIdRepository) {

	operator fun invoke(): String = repository.loadUserId()
}