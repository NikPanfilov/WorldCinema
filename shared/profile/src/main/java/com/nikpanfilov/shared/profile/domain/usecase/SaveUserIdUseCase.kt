package com.nikpanfilov.shared.profile.domain.usecase

import com.nikpanfilov.shared.profile.domain.repository.ProfileRepository
import com.nikpanfilov.shared.profile.domain.repository.UserIdRepository

class SaveUserIdUseCase(private val repository: UserIdRepository, private val profileRepository: ProfileRepository) {

	suspend operator fun invoke() {
		repository.saveUserId(GetProfileUseCase(profileRepository).invoke().userId)
	}
}