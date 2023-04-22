package com.nikpanfilov.chats.list.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.nikpanfilov.chats.list.databinding.FragmentChatsListBinding
import com.nikpanfilov.chats.list.presentation.ChatsListViewModel
import com.nikpanfilov.chats.list.ui.adapter.ChatsAdapter
import com.nikpanfilov.core.navigation.OnFragmentChangedListener
import com.nikpanfilov.extensions.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChatsListFragment : Fragment() {

	companion object {

		fun newInstance() = ChatsListFragment()
	}

	private val binding by lazy { FragmentChatsListBinding.inflate(layoutInflater) }
	private val viewModel by viewModel<ChatsListViewModel>()

	private var listener: OnFragmentChangedListener? = null

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
	): View {
		listener?.onFragmentChanged(this)

		val adapter = ChatsAdapter(viewModel::navigateToChat,viewModel.userId)
		binding.chatsRecyclerView.adapter = adapter
		viewModel.chatsMutableFlow.launch(viewLifecycleOwner.lifecycleScope) {
			adapter.data = it
		}
		binding.backButton.setOnClickListener { viewModel.navigateBack() }

		return binding.root
	}

	override fun onAttach(context: Context) {
		super.onAttach(context)
		if (context is OnFragmentChangedListener) {
			listener = context
		}
	}
}