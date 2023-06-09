package com.nikpanfilov.profile.data.api

import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface AvatarApi {

	@Multipart
	@POST("api/profile/avatar")
	suspend fun setAvatar(@Part photo: MultipartBody.Part)
}