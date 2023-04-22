package com.nikpanfilov.main.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.nikpanfilov.extensions.setImage
import com.nikpanfilov.main.databinding.ItemPosterVerticalBinding
import com.nikpanfilov.shared.movie.domain.entity.Movie

class PosterAdapter(private val toFilm: (Movie) -> Unit) : RecyclerView.Adapter<PosterAdapter.PosterViewHolder>() {

	var data: List<Movie> = emptyList()
		set(newValue) {
			field = newValue
			notifyDataSetChanged()
		}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PosterViewHolder {
		val inflater = LayoutInflater.from(parent.context)
		return PosterViewHolder(ItemPosterVerticalBinding.inflate(inflater, parent, false))
	}

	override fun onBindViewHolder(holder: PosterViewHolder, position: Int) {
		bind(holder.binding.verticalPosterImageView, data[position], toFilm)
	}

	override fun getItemCount() = data.size

	private fun bind(imageView: ImageView, item: Movie, toFilm: (Movie) -> Unit) {
		imageView.setImage(item.poster)

		imageView.setOnClickListener { toFilm(item) }
	}

	class PosterViewHolder(val binding: ItemPosterVerticalBinding) : RecyclerView.ViewHolder(binding.root)
}