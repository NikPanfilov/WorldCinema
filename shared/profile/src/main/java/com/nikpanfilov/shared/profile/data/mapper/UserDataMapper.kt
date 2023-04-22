package com.nikpanfilov.shared.profile

import com.nikpanfilov.shared.profile.data.dto.UserDataDto
import com.nikpanfilov.shared.profile.domain.entity.UserData

internal fun UserDataDto.toEntity() = UserData(
	userId = userId,
	firstName = firstName,
	lastName = lastName,
	email = email,
	avatar = avatar
)