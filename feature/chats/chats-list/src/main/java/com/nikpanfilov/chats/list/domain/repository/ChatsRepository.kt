package com.nikpanfilov.chats.list.domain.repository

import com.nikpanfilov.chats.list.domain.entity.Chat

interface ChatsRepository {

	suspend fun getChats(): List<Chat>
}