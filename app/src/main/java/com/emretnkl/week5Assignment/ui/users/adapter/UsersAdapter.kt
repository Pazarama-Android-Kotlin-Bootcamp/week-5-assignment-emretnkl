package com.emretnkl.week5Assignment.ui.users.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.emretnkl.week5Assignment.data.model.Users
import com.emretnkl.week5Assignment.databinding.ItemUserLayoutBinding

class UsersAdapter : ListAdapter<Users, UsersAdapter.UsersViewHolder>(UsersDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder{
        return UsersViewHolder(
            ItemUserLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class UsersViewHolder(private val binding: ItemUserLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(users: Users){
            binding.dataHolder = users
            binding.executePendingBindings()

        }
    }

    class UsersDiffUtil : DiffUtil.ItemCallback<Users>() {
        override fun areItemsTheSame(oldItem: Users, newItem: Users): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Users, newItem: Users): Boolean {
            return oldItem == newItem
        }

    }



}