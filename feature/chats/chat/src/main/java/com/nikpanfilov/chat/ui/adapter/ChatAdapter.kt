package com.nikpanfilov.chat.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutParams
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.nikpanfilov.chat.R
import com.nikpanfilov.chat.databinding.DateItemBinding
import com.nikpanfilov.chat.databinding.MessageItemBinding
import com.nikpanfilov.chat.databinding.UserMessageItemBinding
import com.nikpanfilov.chat.domain.entity.DateItem
import com.nikpanfilov.chat.domain.entity.ListItem
import com.nikpanfilov.chat.domain.entity.MessageItem
import com.nikpanfilov.chat.domain.entity.UserMessageItem
import com.nikpanfilov.extensions.setImage

class ChatAdapter() : RecyclerView.Adapter<ViewHolder>() {

	companion object {

		const val DATE = 0
		const val USER = 1
		const val NOT_USER = 2

		const val SMALL_MARGIN = 2
	}

	var data: List<ListItem> = emptyList()
		set(newValue) {
			field = newValue
			notifyDataSetChanged()
		}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		val inflater = LayoutInflater.from(parent.context)
		return when (viewType) {
			DATE -> DateItemViewHolder(DateItemBinding.inflate(inflater, parent, false))
			USER -> UserMessageItemViewHolder(UserMessageItemBinding.inflate(inflater, parent, false))
			else -> MessageItemViewHolder(MessageItemBinding.inflate(inflater, parent, false))
		}
	}

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		when (val item = data[position]) {
			is DateItem        -> (holder as DateItemViewHolder).bind(item)
			is UserMessageItem -> (holder as UserMessageItemViewHolder).bind(item)
			else               -> (holder as MessageItemViewHolder).bind(item as MessageItem)
		}
	}

	override fun getItemViewType(position: Int): Int {
		return when (data[position]) {
			is DateItem        -> DATE
			is UserMessageItem -> USER
			else               -> NOT_USER
		}
	}

	override fun getItemCount() = data.size

	class DateItemViewHolder(private val binding: DateItemBinding) : ViewHolder(binding.root) {

		fun bind(data: DateItem) {
			binding.dateTextView.text = data.date
		}
	}

	class MessageItemViewHolder(private val binding: MessageItemBinding) : ViewHolder(binding.root) {

		fun bind(data: MessageItem) {
			with(binding) {
				if (data.authorAvatar != null)
					profileImageView.setImage(data.authorAvatar)
				else
					profileImageView.setImageResource(R.drawable.avatar_placeholder)

				textView.text = data.text
				val authorAndTime = data.authorName + root.context.getString(R.string.divider) + data.creationTime
				authorDateTextView.text = authorAndTime

				//Отступы
				val params = itemView.layoutParams as LayoutParams
				if (data.isPrevInChain)
					params.topMargin = SMALL_MARGIN
				if (data.isNextInChain) {
					params.bottomMargin = SMALL_MARGIN
					profileImageView.visibility = View.INVISIBLE
				}
				itemView.layoutParams = params
			}
		}
	}

	class UserMessageItemViewHolder(private val binding: UserMessageItemBinding) : ViewHolder(binding.root) {

		fun bind(data: UserMessageItem) {
			Log.i("message","yeah")
			with(binding) {
				if (data.authorAvatar != null)
					profileImageView.setImage(data.authorAvatar)
				else
					profileImageView.setImageResource(R.drawable.avatar_placeholder)

				textView.text = data.text
				val authorAndTime = data.authorName + root.context.getString(R.string.divider) + data.creationTime
				authorDateTextView.text = authorAndTime

				//Отступы
				val params = itemView.layoutParams as LayoutParams
				if (data.isPrevInChain)
					params.topMargin = SMALL_MARGIN
				if (data.isNextInChain) {
					params.bottomMargin = SMALL_MARGIN
					profileImageView.visibility = View.INVISIBLE
				}
				itemView.layoutParams = params
			}
		}
	}
}