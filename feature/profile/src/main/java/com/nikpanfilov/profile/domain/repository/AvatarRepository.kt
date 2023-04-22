package com.nikpanfilov.profile.domain.repository

interface AvatarRepository {

	suspend fun setAvatar(photo: ByteArray)
}