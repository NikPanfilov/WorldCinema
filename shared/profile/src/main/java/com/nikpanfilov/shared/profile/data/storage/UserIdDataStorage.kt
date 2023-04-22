package com.nikpanfilov.shared.profile.data.storage

interface UserIdDataStorage {

	fun saveUserId(id: String)
	fun loadUserId(): String
}