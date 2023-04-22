package com.nikpanfilov.chats.list.data.mapper

import com.nikpanfilov.chats.list.data.dto.ChatDto
import com.nikpanfilov.chats.list.data.dto.LastMessageDto
import com.nikpanfilov.chats.list.domain.entity.Chat
import com.nikpanfilov.chats.list.domain.entity.LastMessage

internal fun ChatDto.toEntity() = Chat(chatId = chatId, chatName = chatName, lastMessage =lastMessage.toEntity())

private fun LastMessageDto.toEntity() = LastMessage(
	messageId = messageId,
	creationDateTime = creationDateTime,
	authorId = authorId,
	authorName = authorName,
	authorAvatar = authorAvatar,
	text = text
)