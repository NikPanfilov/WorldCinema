package com.nikpanfilov.collections.presentation

import com.nikpanfilov.shared.collections.domain.entity.Collection

interface ChooseIconRouter {

	fun navigateBack(collection: Collection)
	fun navigateBack()
}