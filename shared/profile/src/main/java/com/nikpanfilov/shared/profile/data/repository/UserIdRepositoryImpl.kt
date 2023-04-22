package com.nikpanfilov.shared.profile.data.repository

import com.nikpanfilov.shared.profile.data.storage.UserIdDataStorage
import com.nikpanfilov.shared.profile.domain.repository.UserIdRepository

class UserIdRepositoryImpl(private val dataStorage: UserIdDataStorage) : UserIdRepository {

	override fun saveUserId(id: String) {
		dataStorage.saveUserId(id)
	}

	override fun loadUserId(): String = dataStorage.loadUserId()
}