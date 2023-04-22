package com.nikpanfilov.chat.domain.usecase

import com.nikpanfilov.chat.domain.entity.Message
import com.nikpanfilov.chat.domain.repository.ChatRepository
import kotlinx.coroutines.flow.StateFlow

class GetMessagesUseCase(private val repository: ChatRepository) {

	suspend operator fun invoke(): StateFlow<List<Message>> = repository.getMessages()
}