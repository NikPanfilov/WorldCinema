package com.nikpanfilov.profile.presentation

sealed class ProfileState {

	object Initial : ProfileState()

	data class Content(val sendState: ProfileSendState) : ProfileState()
}

sealed class ProfileSendState {

	object Input : ProfileSendState()

	object Loading : ProfileSendState()

	object Success : ProfileSendState()

	data class Error(val errorCode: Int) : ProfileSendState()
}