package com.nikpanfilov.chat

import com.nikpanfilov.chat.ui.ChatFragment
import com.nikpanfilov.core.navigation.FragmentDestination

class ChatDestination(private val chatId: String, private val chatName: String) : FragmentDestination {

	override fun createInstance() = ChatFragment.newInstance(chatId, chatName)
}