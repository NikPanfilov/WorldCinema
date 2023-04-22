package com.nikpanfilov.chat.domain.usecase

import com.nikpanfilov.chat.domain.repository.ChatRepository

class ConnectToChatUseCase(private val repository: ChatRepository) {

	operator fun invoke(id: String) = repository.connect(id)
}