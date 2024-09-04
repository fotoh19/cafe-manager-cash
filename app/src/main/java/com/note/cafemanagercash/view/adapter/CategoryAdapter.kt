package com.note.cafemanagercash.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.note.cafemanagercash.R
import com.note.cafemanagercash.databinding.ViewHolderCategoryBinding
import com.note.cafemanagercash.model.CategoryModel
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

class CategoryAdapter :
    ListAdapter<CategoryModel, CategoryAdapter.Viewholder>(DiffCallback()) {

    private var selectedPosition = -1
    private var lastSelectedPosition = -1

    inner class Viewholder(val binding: ViewHolderCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val binding = ViewHolderCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: Viewholder, @SuppressLint("RecyclerView") position: Int) {
        val item = getItem(position)
        holder.binding.titleCategory.text = item.title

        holder.binding.root.setOnClickListener {
            lastSelectedPosition = selectedPosition
            selectedPosition = position
            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(selectedPosition)
        }

        if (selectedPosition == position) {
            holder.binding.titleCategory.setBackgroundResource(R.drawable.orange_bg)
        } else {
            holder.binding.titleCategory.setBackgroundResource(R.drawable.editetextbg)
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<CategoryModel>() {
        override fun areItemsTheSame(oldItem: CategoryModel, newItem: CategoryModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: CategoryModel, newItem: CategoryModel): Boolean {
            return oldItem.title == newItem.title
        }
    }
}
