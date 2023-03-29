package com.nikpanfilov.signup.data.mapper

import com.nikpanfilov.signup.data.dto.RegistrationBodyDto
import com.nikpanfilov.signup.domain.entity.RegistrationBody

internal fun RegistrationBody.toDto() = RegistrationBodyDto(
	email = email,
	password = password,
	firstName = firstName,
	lastName = lastName
)