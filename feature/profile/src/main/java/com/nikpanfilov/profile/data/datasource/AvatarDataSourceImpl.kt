package com.nikpanfilov.profile.data.datasource

import com.nikpanfilov.profile.data.api.AvatarApi
import com.nikpanfilov.profile.data.mapper.byteArrayToMultipartBody

class AvatarDataSourceImpl(private val api: AvatarApi) : AvatarDataSource {

	companion object {

		const val IMAGE_NAME = "original"
		const val IMAGE_TYPE = "image/jpeg"
	}

	override suspend fun setAvatar(photo: ByteArray) = api.setAvatar(
        byteArrayToMultipartBody(byteArray = photo, name = IMAGE_NAME, type = IMAGE_TYPE)
    )
}