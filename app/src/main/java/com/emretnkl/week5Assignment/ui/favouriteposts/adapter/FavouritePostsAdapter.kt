package com.emretnkl.week5Assignment.ui.favouriteposts.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.emretnkl.week5Assignment.data.model.Post
import com.emretnkl.week5Assignment.data.model.PostDTO
import com.emretnkl.week5Assignment.databinding.ItemFavouritePostLayoutBinding

class FavouritePostsAdapter : ListAdapter<PostDTO, FavouritePostsAdapter.FavouritePostViewHolder>(FavouritePostsDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouritePostViewHolder {
        return FavouritePostViewHolder(
            ItemFavouritePostLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FavouritePostViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class FavouritePostViewHolder(private val binding: ItemFavouritePostLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(post: PostDTO){
            binding.dataHolder = post
            binding.executePendingBindings()
        }
    }

    class FavouritePostsDiffUtil : DiffUtil.ItemCallback<PostDTO>() {
        override fun areItemsTheSame(oldItem: PostDTO, newItem: PostDTO): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PostDTO, newItem: PostDTO): Boolean {
            return oldItem == newItem
        }

    }
}