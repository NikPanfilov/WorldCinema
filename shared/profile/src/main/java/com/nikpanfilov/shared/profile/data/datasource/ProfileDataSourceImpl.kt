package com.nikpanfilov.shared.profile.data.datasource

import com.nikpanfilov.shared.profile.data.api.ProfileApi
import com.nikpanfilov.shared.profile.domain.entity.UserData
import com.nikpanfilov.shared.profile.toEntity

class ProfileDataSourceImpl(private val api: ProfileApi) : ProfileDataSource {

	override suspend fun getProfile(): UserData = api.getProfile().toEntity()
}