package com.nikpanfilov.collections.presentation

import androidx.lifecycle.ViewModel
import com.nikpanfilov.shared.collections.domain.entity.Collection

class ChooseIconViewModel(
	private val collection: Collection,
	private val router: ChooseIconRouter
) : ViewModel() {

	fun navigateBack() {
		router.navigateBack()
	}

	fun navigateBack(iconId: Int) {
		router.navigateBack(Collection(collectionId = collection.collectionId, name = collection.name, iconRes = iconId))
	}
}