package com.nikpanfilov.signin.ui

import android.content.Context
import android.util.Log
import androidx.lifecycle.LifecycleCoroutineScope
import com.nikpanfilov.extensions.bindError
import com.nikpanfilov.extensions.bindText
import com.nikpanfilov.extensions.launch
import com.nikpanfilov.signin.databinding.FragmentSignInBinding
import com.nikpanfilov.signin.presentation.SignInSendState
import com.nikpanfilov.signin.presentation.SignInState
import com.nikpanfilov.signin.presentation.SignInViewModel

internal fun FragmentSignInBinding.bindData(viewModel: SignInViewModel, scope: LifecycleCoroutineScope) {
	with(viewModel) {
		buttonSignIn.setOnClickListener { signIn() }
		buttonToSignUp.setOnClickListener { navigateToSignUp() }

		editTextEmail.bindText(scope, emailMutableFlow)
		editTextPassword.bindText(scope, passwordMutableFlow)

		val context = emailTextInputLayout.context

		emailMutableFlow.launch(scope) {
			emailTextInputLayout.bindError(context.getString(getEmailError()))
		}

		passwordMutableFlow.launch(scope) {
			passwordTextInputLayout.bindError(context.getString(getPasswordError()))
		}

		stateFlow.launch(scope) {
			Log.i("state", it.toString())
			if (it is SignInState.Content) {
				if (it.sendState is SignInSendState.Success) {
					navigateToMain()
				}
			}
		}

		/*scope.launch {
			emailMutableFlow.onEach {
				emailTextInputLayout.bindError(context.getString(getEmailError()))
			}.collect()
		}

		scope.launch {
			stateFlow.onEach {
				if (it is SignInState.Content) {
					if (it.sendState is SignInSendState.Success) {
						navigateToMain()
					}
				}
			}.collect()
		}

		scope.launch {
			passwordMutableFlow.onEach {
				passwordTextInputLayout.bindError(context.getString(getPasswordError()))
			}.collect()
		}*/
	}
}

private fun Context.getString(id: Int?) = id?.let { this.getString(it) }