package com.nikpanfilov.shared.profile.data.datasource

import com.nikpanfilov.shared.profile.domain.entity.UserData

interface ProfileDataSource {

	suspend fun getProfile(): UserData
}