package com.nikpanfilov.chat.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.nikpanfilov.chat.databinding.FragmentChatBinding
import com.nikpanfilov.chat.presentation.ChatViewModel
import com.nikpanfilov.core.navigation.OnFragmentChangedListener
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ChatFragment : Fragment() {

	companion object {

		private const val CHAT = "chat"
		fun newInstance(chatId: String, chatName: String) = ChatFragment().apply {
			this.chatName = chatName
			arguments = bundleOf(CHAT to chatId)
		}
	}

	private var chatName: String = ""

	private val binding by lazy { FragmentChatBinding.inflate(layoutInflater) }
	private val viewModel by viewModel<ChatViewModel> {
		parametersOf(arguments?.get(CHAT))
	}

	private var listener: OnFragmentChangedListener? = null

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
	): View {
		listener?.onFragmentChanged(this)
		binding.chatsNameTextView.text = chatName
		binding.bindData(viewModel, viewLifecycleOwner.lifecycleScope)

		requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
			viewModel.navigateBack()
		}

		return binding.root
	}

	override fun onAttach(context: Context) {
		super.onAttach(context)
		if (context is OnFragmentChangedListener) {
			listener = context
		}
	}
}