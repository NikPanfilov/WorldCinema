package com.nikpanfilov.movie.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nikpanfilov.movie.databinding.ShotItemBinding

class ShotsAdapter : RecyclerView.Adapter<ShotsAdapter.ShotsViewHolder>() {

	var data: List<String> = emptyList()
		set(newValue) {
			field = newValue
			notifyDataSetChanged()
		}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShotsViewHolder {
		val inflater = LayoutInflater.from(parent.context)
		val binding = ShotItemBinding.inflate(inflater, parent, false)

		return ShotsViewHolder(binding)
	}

	override fun onBindViewHolder(holder: ShotsViewHolder, position: Int) {
		Glide.with(holder.binding.shotImageView).load(data[position]).into(holder.binding.shotImageView)
	}

	override fun getItemCount() = data.size

	class ShotsViewHolder(val binding: ShotItemBinding) : RecyclerView.ViewHolder(binding.root)
}