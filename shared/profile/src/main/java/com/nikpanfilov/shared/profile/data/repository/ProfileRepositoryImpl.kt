package com.nikpanfilov.shared.profile.data.repository

import com.nikpanfilov.shared.profile.data.datasource.ProfileDataSource
import com.nikpanfilov.shared.profile.domain.entity.UserData
import com.nikpanfilov.shared.profile.domain.repository.ProfileRepository

class ProfileRepositoryImpl(private val dataSource: ProfileDataSource) : ProfileRepository {

	override suspend fun getProfile(): UserData = dataSource.getProfile()
}