package com.nikpanfilov.profile.data.datasource

interface AvatarDataSource {

	suspend fun setAvatar(photo: ByteArray)
}