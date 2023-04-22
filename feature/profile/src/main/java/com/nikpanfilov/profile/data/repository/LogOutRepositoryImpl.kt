package com.nikpanfilov.profile.data.repository

import com.nikpanfilov.profile.data.storage.LogOutDataStorage
import com.nikpanfilov.profile.domain.repository.LogOutRepository

class LogOutRepositoryImpl(private val storage: LogOutDataStorage) : LogOutRepository {

	override fun logOut() = storage.logOut()
}