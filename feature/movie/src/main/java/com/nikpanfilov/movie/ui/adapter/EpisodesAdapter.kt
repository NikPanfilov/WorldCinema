package com.nikpanfilov.movie.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nikpanfilov.extensions.setImage
import com.nikpanfilov.movie.databinding.EpisodeItemBinding
import com.nikpanfilov.movie.domain.entity.Episode

class EpisodesAdapter(private val toEpisode: (Episode) -> Unit) : RecyclerView.Adapter<EpisodesAdapter.EpisodeViewHolder>() {

	var data: List<Episode> = emptyList()
		set(newValue) {
			field = newValue
			notifyDataSetChanged()
		}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
		val inflater = LayoutInflater.from(parent.context)
		val binding = EpisodeItemBinding.inflate(inflater, parent, false)

		return EpisodeViewHolder(binding)
	}

	override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
		with(holder.binding) {
			previewImageView.setImage(data[position].preview)
			episodeNameTextView.text = data[position].name
			episodeDescriptionTextView.text = data[position].description
			yearTextView.text = data[position].year

			root.setOnClickListener { toEpisode(data[position]) }
		}
	}

	override fun getItemCount() = data.size

	class EpisodeViewHolder(val binding: EpisodeItemBinding) : RecyclerView.ViewHolder(binding.root)
}