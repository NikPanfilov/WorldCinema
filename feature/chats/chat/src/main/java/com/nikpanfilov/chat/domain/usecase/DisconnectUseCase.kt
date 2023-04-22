package com.nikpanfilov.chat.domain.usecase

import com.nikpanfilov.chat.domain.repository.ChatRepository

class DisconnectUseCase(private val repository: ChatRepository) {

	operator fun invoke() = repository.disconnect()
}