package com.nikpanfilov.shared.profile.domain.usecase

import com.nikpanfilov.shared.profile.domain.entity.UserData
import com.nikpanfilov.shared.profile.domain.repository.ProfileRepository

class GetProfileUseCase(private val repository: ProfileRepository) {

	suspend operator fun invoke(): UserData = repository.getProfile()
}