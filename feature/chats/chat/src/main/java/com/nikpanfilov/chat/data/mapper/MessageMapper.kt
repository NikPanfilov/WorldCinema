package com.nikpanfilov.chat.data.mapper

import com.nikpanfilov.chat.data.dto.MessageDto
import com.nikpanfilov.chat.domain.entity.Message

internal fun MessageDto.toEntity() = Message(
	messageId = messageId,
	creationDateTime = creationDateTime,
	authorId = authorId,
	authorName = authorName,
	authorAvatar = authorAvatar,
	text = text
)