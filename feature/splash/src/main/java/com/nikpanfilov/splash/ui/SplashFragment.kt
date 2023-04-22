package com.nikpanfilov.splash.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.nikpanfilov.core.navigation.OnFragmentChangedListener
import com.nikpanfilov.splash.R
import com.nikpanfilov.splash.presentation.SplashSendState
import com.nikpanfilov.splash.presentation.SplashState
import com.nikpanfilov.splash.presentation.SplashViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashFragment : Fragment() {

	companion object {

		fun newInstance() = SplashFragment()
	}

	private val viewModel by viewModel<SplashViewModel>()

	private var listener: OnFragmentChangedListener? = null

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		listener?.onFragmentChanged(this)
		return inflater.inflate(R.layout.splash_fragment, container, false)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		viewLifecycleOwner.lifecycleScope.launchWhenResumed {
			viewModel.stateFlow.onEach {
				if (it is SplashState.Content) {
					when (it.sendState) {
						is SplashSendState.Success -> viewModel.navigateToMain()
						is SplashSendState.Error   -> viewModel.navigateToSignIn()

						else                       -> {}
					}
				}
			}.collect()
		}

		super.onViewCreated(view, savedInstanceState)
	}

	override fun onAttach(context: Context) {
		super.onAttach(context)
		if (context is OnFragmentChangedListener) {
			listener = context
		}
	}
}
