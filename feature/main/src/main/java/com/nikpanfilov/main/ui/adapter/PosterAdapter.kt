package com.nikpanfilov.main.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nikpanfilov.main.databinding.ItemPosterHorizontalBinding
import com.nikpanfilov.main.databinding.ItemPosterVerticalBinding
import com.nikpanfilov.main.domain.entity.Movie

class PosterAdapter(private val toFilm: (String) -> Unit, private val isHorizontal: Boolean) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

	var data: List<Movie> = emptyList()
		set(newValue) {
			field = newValue
			notifyDataSetChanged()
		}

	companion object {

		const val HORIZONTAL = 1
		const val VERTICAL = 0
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
		val inflater = LayoutInflater.from(parent.context)
		return when (viewType) {
			HORIZONTAL -> HorizontalPosterViewHolder(ItemPosterHorizontalBinding.inflate(inflater, parent, false))
			else       -> VerticalPosterViewHolder(ItemPosterVerticalBinding.inflate(inflater, parent, false))
		}
	}

	override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
		if (isHorizontal) {
			(holder as HorizontalPosterViewHolder)
			bind(holder.binding.horizontalPosterImageView, data[position], toFilm)
		} else {
			(holder as VerticalPosterViewHolder)
			bind(holder.binding.verticalPosterImageView, data[position], toFilm)
		}
	}

	override fun getItemViewType(position: Int): Int {
		return if (isHorizontal) HORIZONTAL else VERTICAL
	}

	override fun getItemCount() = data.size

	private fun bind(imageView: ImageView, item: Movie, toFilm: (String) -> Unit) {
		Glide.with(imageView)
			.load(item.poster) // загрузка изображения по ссылке
			.into(imageView); // установка изображения в ImageView

		imageView.setOnClickListener { toFilm(item.movieId) }
	}

	class VerticalPosterViewHolder(val binding: ItemPosterVerticalBinding) : RecyclerView.ViewHolder(binding.root)

	class HorizontalPosterViewHolder(val binding: ItemPosterHorizontalBinding) : RecyclerView.ViewHolder(binding.root)
}