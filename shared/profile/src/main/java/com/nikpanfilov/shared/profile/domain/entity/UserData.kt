package com.nikpanfilov.shared.profile.domain.entity

data class UserData(
	val userId: String,
	val firstName: String,
	val lastName: String,
	val email: String,
	val avatar: String?
)
