package com.nikpanfilov.profile.data.repository

import com.nikpanfilov.profile.data.datasource.AvatarDataSource
import com.nikpanfilov.profile.domain.repository.AvatarRepository

class AvatarRepositoryImpl(private val dataSource: AvatarDataSource) : AvatarRepository {

	override suspend fun setAvatar(photo: ByteArray) = dataSource.setAvatar(photo)
}