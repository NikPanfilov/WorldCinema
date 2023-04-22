package com.nikpanfilov.collections.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nikpanfilov.collections.databinding.CollectionItemBinding
import com.nikpanfilov.shared.collections.domain.entity.Collection

class CollectionAdapter(private val toCollection: (Collection) -> Unit) : RecyclerView.Adapter<CollectionAdapter.CollectionViewHolder>() {

	var data: List<Collection> = emptyList()
		set(newValue) {
			field = newValue
			notifyDataSetChanged()
		}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectionViewHolder {
		val inflater = LayoutInflater.from(parent.context)
		val binding = CollectionItemBinding.inflate(inflater, parent, false)

		return CollectionViewHolder(binding)
	}

	override fun onBindViewHolder(holder: CollectionViewHolder, position: Int) {
		with(holder.binding) {
			collectionIconImageView.setImageResource(data[position].iconRes)
			collectionNameTextView.text = data[position].name

			root.setOnClickListener { toCollection(data[position]) }
		}
	}

	override fun getItemCount() = data.size

	class CollectionViewHolder(val binding: CollectionItemBinding) : RecyclerView.ViewHolder(binding.root)
}