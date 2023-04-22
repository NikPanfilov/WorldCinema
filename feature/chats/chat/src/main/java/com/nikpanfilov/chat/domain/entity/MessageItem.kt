package com.nikpanfilov.chat.domain.entity

import com.nikpanfilov.chat.R

data class MessageItem(
	val creationTime: String,
	val authorName: String,
	val authorAvatar: String?,
	val text: String,
	var isPrevInChain: Boolean = false,
	var isNextInChain: Boolean = false
) : ListItem {

	override val viewType: Int
		get() = R.layout.message_item
}