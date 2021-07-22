package com.cherrio.leap.features.allnews

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cherrio.leap.data.model.Data
import com.cherrio.leap.databinding.NewsItemBinding

class NewsAdapter(private val cNewsAdapter: CNewsAdapter) : PagingDataAdapter<Data, NewsAdapter.NewsViewHolder>(DIFF) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            NewsItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            cNewsAdapter
        )
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val data = getItem(position)
        data?.let {
            holder.bind(it)
        }
    }


    inner class NewsViewHolder(
        private val binding: NewsItemBinding,
        private val cNewsAdapter: CNewsAdapter
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Data) {
            binding.apply {
                newsAuthor.text = data.author
                newsDate.text = data.published_at
                newsTitle.text = data.title
                newsDesc.text = data.description
                imageView.apply {
                    Glide.with(this).load(data.image).into(this)
                }

                root.setOnLongClickListener {
                    cNewsAdapter.addToFavorites(data)
                }
            }
        }
    }

    companion object {
        val DIFF = object : DiffUtil.ItemCallback<Data>() {
            override fun areItemsTheSame(oldItem: Data, newItem: Data) = oldItem.url == newItem.url

            override fun areContentsTheSame(oldItem: Data, newItem: Data) =
                oldItem.url == newItem.url
        }
    }
}