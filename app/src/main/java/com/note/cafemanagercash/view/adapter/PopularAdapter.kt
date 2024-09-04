package com.note.cafemanagercash.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.note.cafemanagercash.R
import com.note.cafemanagercash.databinding.ViewholderPopularBinding
import com.note.cafemanagercash.model.ItemsModel

class PopularAdapter : ListAdapter<ItemsModel, PopularAdapter.ViewHolder>(DiffCallback()) {

    inner class ViewHolder(val binding: ViewholderPopularBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ViewholderPopularBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.titleTxt.text = item.title
        holder.binding.priceTxt.text = "$-" + item.price.toString()
        holder.binding.ratingBar.rating = item.rating.toFloat()
        holder.binding.extraTxt.text = item.extra

        Glide.with(holder.itemView)
            .load(item.picUrl[0])
            .into(holder.binding.pic)

        holder.itemView.setOnClickListener {

            it.findNavController().navigate(R.id.action_mainFragment_to_detailFragment)
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<ItemsModel>() {
        override fun areItemsTheSame(oldItem: ItemsModel, newItem: ItemsModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ItemsModel, newItem: ItemsModel): Boolean {
            return oldItem == newItem
        }
    }
}
