package com.cherrio.leap.features.fav

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cherrio.leap.data.model.Data
import com.cherrio.leap.data.model.DataEntity
import com.cherrio.leap.databinding.NewsItemBinding
import com.cherrio.leap.features.allnews.NewsAdapter

class FavAdapter: RecyclerView.Adapter<FavAdapter.FavViewHolder>() {

    private val list = arrayListOf<DataEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavViewHolder {
        return FavViewHolder(createBinding(parent))
    }

    override fun onBindViewHolder(holder: FavViewHolder, position: Int) {
        //bind
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    fun submitList(list: List<DataEntity>){
        this.list.addAll(list)
        notifyDataSetChanged()
    }


    inner class FavViewHolder(private val binding: NewsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: DataEntity){
            binding.apply {
                newsAuthor.text = data.author
                newsDate.text = data.published_at
                newsTitle.text = data.title
                newsDesc.text = data.description
                imageView.apply {
                    Glide.with(this).load(data.image).into(this)
                }
            }
        }
    }

    companion object{
        fun createBinding(parent: ViewGroup):NewsItemBinding{
            val inflater = LayoutInflater.from(parent.context)
            return NewsItemBinding.inflate(inflater,parent,false)
        }
    }

}