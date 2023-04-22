package com.nikpanfilov.collections.ui

import android.view.View
import androidx.lifecycle.LifecycleCoroutineScope
import com.nikpanfilov.collections.R
import com.nikpanfilov.collections.databinding.FragmentChooseIconBinding
import com.nikpanfilov.collections.databinding.FragmentCollectionEditBinding
import com.nikpanfilov.collections.databinding.FragmentCollectionMoviesBinding
import com.nikpanfilov.collections.databinding.FragmentCollectionsBinding
import com.nikpanfilov.collections.presentation.ChooseIconViewModel
import com.nikpanfilov.collections.presentation.CollectionMoviesViewModel
import com.nikpanfilov.collections.presentation.CollectionsViewModel
import com.nikpanfilov.collections.presentation.EditCollectionViewModel
import com.nikpanfilov.collections.ui.adapter.CollectionAdapter
import com.nikpanfilov.collections.ui.adapter.IconAdapter
import com.nikpanfilov.collections.ui.adapter.MovieAdapter
import com.nikpanfilov.extensions.launch
import com.nikpanfilov.shared.collections.FAVOURITE
import com.nikpanfilov.shared.collections.domain.entity.Collection
import com.nikpanfilov.shared.collections.iconsId

internal fun FragmentCollectionsBinding.bindData(viewModel: CollectionsViewModel, scope: LifecycleCoroutineScope) {
	addCollectionImageView.setOnClickListener { viewModel.createCollection() }

	val adapter = CollectionAdapter(viewModel::navigateToMovieCollection)
	collectionsRecyclerView.adapter = adapter
	viewModel.collectionsMutableFlow.launch(scope) { collections ->
		if (collections != null) {
			collections.forEach {
				if (it.iconRes !in iconsId)
					it.iconRes = iconsId[0]
			}

			adapter.data = collections
		}
	}
}

internal fun FragmentCollectionMoviesBinding.bindData(viewModel: CollectionMoviesViewModel, scope: LifecycleCoroutineScope) {
	editCollectionImageView.setOnClickListener { viewModel.navigateToCollectionEdit() }
	backImageView.setOnClickListener { viewModel.navigateBack() }

	collectionNameTextView.text = viewModel.collection.name
	if (viewModel.collection.name == FAVOURITE) {
		editCollectionImageView.visibility = View.GONE
	} else {
		editCollectionImageView.visibility = View.VISIBLE
	}

	val adapter = MovieAdapter()
	moviesRecyclerView.adapter = adapter
	viewModel.moviesMutableFlow.launch(scope) {
		if (it != null) adapter.data = it
	}
}

internal fun FragmentCollectionEditBinding.bindData(viewModel: EditCollectionViewModel, scope: LifecycleCoroutineScope) {
	with(viewModel) {
		if (collection.collectionId != "") {
			toolbarTitleTextView.text = root.context.getString(R.string.edit_collection)

			deleteButton.visibility = View.VISIBLE
			deleteButton.setOnClickListener {
				deleteCollection(collection.collectionId)
			}
			saveButton.setOnClickListener { viewModel.saveCollection(nameEditText.text.toString(), collection.iconRes) }
		} else {
			toolbarTitleTextView.text = root.context.getString(R.string.create_collection)
			deleteButton.visibility = View.GONE
			saveButton.setOnClickListener { viewModel.createCollection(nameEditText.text.toString(), collection.iconRes) }
		}

		nameEditText.setText(collection.name)
		iconImageView.setImageResource(collection.iconRes)
		chooseIconButton.setOnClickListener {
			viewModel.navigateToIconChoose(
				Collection(
					collectionId = collection.collectionId,
					name = nameEditText.text.toString(),
					iconRes = collection.iconRes
				)
			)
		}
		backButton.setOnClickListener { viewModel.navigateBack() }
	}
}

internal fun FragmentChooseIconBinding.bindData(viewModel: ChooseIconViewModel) {
	backButton.setOnClickListener { viewModel.navigateBack() }

	val adapter = IconAdapter(viewModel::navigateBack)
	iconRecyclerView.adapter = adapter
	adapter.data = iconsId
}