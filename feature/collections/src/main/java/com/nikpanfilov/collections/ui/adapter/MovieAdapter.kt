package com.nikpanfilov.collections.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nikpanfilov.collections.databinding.MovieItemBinding
import com.nikpanfilov.extensions.setImage
import com.nikpanfilov.shared.collections.domain.entity.Movie

class MovieAdapter() : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

	var data: List<Movie> = emptyList()
		set(newValue) {
			field = newValue
			notifyDataSetChanged()
		}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
		val inflater = LayoutInflater.from(parent.context)
		val binding = MovieItemBinding.inflate(inflater, parent, false)

		return MovieViewHolder(binding)
	}

	override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
		with(holder.binding) {
			posterImageView.setImage(data[position].poster)
			movieNameTextView.text = data[position].name
			movieDescriptionTextView.text = data[position].description

			//TODO(Сделать удаление фильма)
			root.setOnClickListener { }
		}
	}

	override fun getItemCount() = data.size

	class MovieViewHolder(val binding: MovieItemBinding) : RecyclerView.ViewHolder(binding.root)
}