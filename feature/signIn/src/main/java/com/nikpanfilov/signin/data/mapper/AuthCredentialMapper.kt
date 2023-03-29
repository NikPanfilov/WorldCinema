package com.nikpanfilov.signin.data.mapper

import com.nikpanfilov.signin.data.dto.AuthCredentialDto
import com.nikpanfilov.signin.domain.entity.AuthCredential

internal fun AuthCredential.toDto() = AuthCredentialDto(email = email, password = password)