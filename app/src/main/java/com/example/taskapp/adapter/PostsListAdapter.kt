package com.example.taskapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.taskapp.databinding.RowPostListBinding
import com.example.taskapp.model.PostModels

class PostsListAdapter : PagingDataAdapter<PostModels, PostsListAdapter.PostsViewHolder>(POST_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        val binding = RowPostListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        val post = getItem(position)
        post?.let { holder.bind(it) }
    }

    class PostsViewHolder(private val binding: RowPostListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(postModels: PostModels) {
            binding.posts = postModels
            binding.executePendingBindings()
        }
    }

    companion object {
        private val POST_COMPARATOR = object : DiffUtil.ItemCallback<PostModels>() {
            override fun areItemsTheSame(oldItem: PostModels, newItem: PostModels): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: PostModels, newItem: PostModels): Boolean {
                return oldItem == newItem
            }
        }
    }
}
