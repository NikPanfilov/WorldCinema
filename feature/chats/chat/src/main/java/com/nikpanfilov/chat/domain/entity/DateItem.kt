package com.nikpanfilov.chat.domain.entity

import com.nikpanfilov.chat.R

data class DateItem(
	val date: String
) : ListItem {

	override val viewType: Int
		get() = R.layout.date_item
}