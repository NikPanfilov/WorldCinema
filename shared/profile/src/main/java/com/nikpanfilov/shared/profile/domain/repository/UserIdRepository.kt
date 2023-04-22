package com.nikpanfilov.shared.profile.domain.repository

interface UserIdRepository {

	fun saveUserId(id: String)
	fun loadUserId(): String
}