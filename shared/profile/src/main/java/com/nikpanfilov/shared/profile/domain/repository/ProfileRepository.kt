package com.nikpanfilov.shared.profile.domain.repository

import com.nikpanfilov.shared.profile.domain.entity.UserData

interface ProfileRepository {

	suspend fun getProfile(): UserData
}