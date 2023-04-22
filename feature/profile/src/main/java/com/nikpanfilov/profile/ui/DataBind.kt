package com.nikpanfilov.profile.ui

import android.app.AlertDialog
import androidx.lifecycle.LifecycleCoroutineScope
import com.nikpanfilov.extensions.launch
import com.nikpanfilov.extensions.setImage
import com.nikpanfilov.profile.R
import com.nikpanfilov.profile.databinding.DialogImageSorceBinding
import com.nikpanfilov.profile.databinding.DialogImageUrlBinding
import com.nikpanfilov.profile.databinding.FragmentProfileBinding
import com.nikpanfilov.profile.presentation.ProfileViewModel

internal fun FragmentProfileBinding.bindData(viewModel: ProfileViewModel, scope: LifecycleCoroutineScope) {
	with(viewModel) {
		chatsView.setOnClickListener { navigateToChats() }
		logOutButton.setOnClickListener { logout() }

		userDataFlow.launch(scope) {
			if (it != null) {
				val name = it.firstName + it.lastName
				nameTextView.text = name
				emailTextView.text = it.email

				if (it.avatar != null)
					profileImageView.setImage(it.avatar!!)
			}
		}
	}
}

internal fun DialogImageSorceBinding.bindData(showDialog: () -> Unit) {
	fromInternetButton.setOnClickListener {
		AlertDialog.Builder(root.context).setView(R.layout.dialog_image_url).show()
	}
}

internal fun DialogImageUrlBinding.bindData(viewModel: ProfileViewModel) {
	getImageFromUrlButton.setOnClickListener {
		if (imageUrlTextView.text != "")
			viewModel.setAvatar(imageUrlTextView.text.toString())
	}
}