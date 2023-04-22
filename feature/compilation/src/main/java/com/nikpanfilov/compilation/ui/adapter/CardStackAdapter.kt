package com.nikpanfilov.compilation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nikpanfilov.compilation.databinding.CardItemBinding
import com.nikpanfilov.extensions.setImage
import com.nikpanfilov.shared.movie.domain.entity.Movie

class CardStackAdapter : ListAdapter<Movie, CardStackAdapter.CardStackViewHolder>(DiffCallBack) {

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardStackViewHolder {
		val inflater = LayoutInflater.from(parent.context)
		val binding = CardItemBinding.inflate(inflater, parent, false)

		return CardStackViewHolder(binding)
	}

	override fun onBindViewHolder(holder: CardStackViewHolder, position: Int) {
		val item = getItem(position)

		with(holder.binding) {
			posterImageView.setImage(item.poster)
		}
	}

	class CardStackViewHolder(val binding: CardItemBinding) : RecyclerView.ViewHolder(binding.root)

	object DiffCallBack : DiffUtil.ItemCallback<Movie>() {

		override fun areItemsTheSame(oldItem: Movie, newItem: Movie) = oldItem == newItem

		override fun areContentsTheSame(oldItem: Movie, newItem: Movie) = oldItem.movieId == newItem.movieId
	}
}