package com.nikpanfilov.chats.list

import com.nikpanfilov.chats.list.ui.ChatsListFragment
import com.nikpanfilov.core.navigation.FragmentDestination

class ChatsListDestination() : FragmentDestination {

	override fun createInstance() = ChatsListFragment.newInstance()
}