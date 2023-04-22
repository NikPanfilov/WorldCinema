package com.nikpanfilov.chats.list.ui.adapter

import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nikpanfilov.chats.list.R
import com.nikpanfilov.chats.list.databinding.ChatItemBinding
import com.nikpanfilov.chats.list.domain.entity.Chat

class ChatsAdapter(private val toChat: (String, String) -> Unit, private val userId: String) : RecyclerView.Adapter<ChatsAdapter.ChatsViewHolder>() {

	var data: List<Chat> = emptyList()
		set(newValue) {
			field = newValue
			notifyDataSetChanged()
		}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatsViewHolder {
		val inflater = LayoutInflater.from(parent.context)
		val binding = ChatItemBinding.inflate(inflater, parent, false)

		return ChatsViewHolder(binding)
	}

	override fun onBindViewHolder(holder: ChatsViewHolder, position: Int) {
		with(holder.binding) {
			val message = data[position].lastMessage
			movieNameTextView.text = data[position].chatName

			val color = root.context.getColor(R.color.divider_color)
			if (message.authorId != userId)
				messageTextView.text = buildMessage(message.authorName, message.text, color)
			else {
				messageTextView.text = buildMessage(root.context.getString(R.string.you), message.text, color)
			}

			root.setOnClickListener { toChat(data[position].chatId, data[position].chatName) }
		}
	}

	override fun getItemCount() = data.size

	private fun buildMessage(authorName: String, text: String, color: Int): SpannableStringBuilder {

		val author = authorName.substringBefore(" ")
		val spannable = SpannableStringBuilder("$author: $text")

		spannable.setSpan(ForegroundColorSpan(color), 0, author.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

		return spannable
	}

	class ChatsViewHolder(val binding: ChatItemBinding) : RecyclerView.ViewHolder(binding.root)
}