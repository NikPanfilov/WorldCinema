package com.nikpanfilov.splash.data.repository

import com.nikpanfilov.splash.data.storage.FirstStartDataStorage
import com.nikpanfilov.splash.domain.repository.FirstStartRepository

class FirstStartRepositoryImpl(private val dataStorage: FirstStartDataStorage) : FirstStartRepository {

	override fun isFirstStart(): Boolean = dataStorage.isFirstStart()

}