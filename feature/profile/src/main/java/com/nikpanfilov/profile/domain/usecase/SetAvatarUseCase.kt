package com.nikpanfilov.profile.domain.usecase

import com.nikpanfilov.profile.domain.repository.AvatarRepository

class SetAvatarUseCase(private val repository: AvatarRepository) {

	suspend operator fun invoke(photo: ByteArray) = repository.setAvatar(photo)
}