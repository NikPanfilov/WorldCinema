package com.nikpanfilov.core.navigation.holders

import java.io.Serializable

data class MovieInfoHolder(
	val id: String,
	val name: String,
	val poster: String,
	val years: String
) : Serializable
