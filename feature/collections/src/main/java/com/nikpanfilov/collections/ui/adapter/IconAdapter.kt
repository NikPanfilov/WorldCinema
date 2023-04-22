package com.nikpanfilov.collections.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nikpanfilov.collections.databinding.IconItemBinding

class IconAdapter(private val chooseIcon: (Int) -> Unit) : RecyclerView.Adapter<IconAdapter.IconViewHolder>() {

	var data: List<Int> = emptyList()
		set(newValue) {
			field = newValue
			notifyDataSetChanged()
		}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IconViewHolder {
		val inflater = LayoutInflater.from(parent.context)
		val binding = IconItemBinding.inflate(inflater, parent, false)

		return IconViewHolder(binding)
	}

	override fun onBindViewHolder(holder: IconViewHolder, position: Int) {
		with(holder.binding) {
			root.setOnClickListener { chooseIcon(data[position]) }
			iconImageView.setImageResource(data[position])
		}
	}

	override fun getItemCount() = data.size

	class IconViewHolder(val binding: IconItemBinding) : RecyclerView.ViewHolder(binding.root)
}