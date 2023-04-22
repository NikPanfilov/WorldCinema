package com.nikpanfilov.chat.ui

import androidx.lifecycle.LifecycleCoroutineScope
import com.nikpanfilov.chat.R
import com.nikpanfilov.chat.databinding.FragmentChatBinding
import com.nikpanfilov.chat.presentation.ChatViewModel
import com.nikpanfilov.chat.ui.adapter.ChatAdapter
import com.nikpanfilov.extensions.launch

internal fun FragmentChatBinding.bindData(viewModel: ChatViewModel, scope: LifecycleCoroutineScope) {
	with(viewModel) {
		backButton.setOnClickListener { navigateBack() }

		sendMessageButton.setOnClickListener {
			if (messageEditText.text.toString() != "")
				sendMessage(messageEditText.text.toString())
			else
				messageTextInputLayout.error = root.context.getString(R.string.message_error)
		}

		val adapter = ChatAdapter()
		chatRecyclerView.adapter = adapter
		messagesFlow.launch(scope) {
			if (it.isNotEmpty())
				adapter.data = it.toItems()
		}
	}
}