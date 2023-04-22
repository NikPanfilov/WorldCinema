package com.nikpanfilov.profile.data.mapper

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody

internal fun byteArrayToMultipartBody(byteArray: ByteArray, name: String, type: String): MultipartBody.Part {
	val imageRequestBody = byteArray.toRequestBody(type.toMediaTypeOrNull())
	return MultipartBody.Part.createFormData(name, name, imageRequestBody)
}