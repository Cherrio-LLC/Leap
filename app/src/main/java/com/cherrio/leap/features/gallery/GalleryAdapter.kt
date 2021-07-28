package com.cherrio.leap.features.gallery

import android.util.Size
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.cherrio.leap.data.model.ImageFolder
import com.cherrio.leap.data.model.Images
import com.cherrio.leap.databinding.GalleryItemBinding
import com.cherrio.leap.databinding.NewsItemBinding
import com.cherrio.leap.utils.loadWithProgressbar

class GalleryAdapter: RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder>() {

    private var list = mutableListOf<ImageFolder>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        return GalleryViewHolder(createBinding(parent))
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount()= list.size

    fun submitList(list: MutableList<ImageFolder>){
      this.list = list
      notifyDataSetChanged()
    }


    inner class GalleryViewHolder(private val binding: GalleryItemBinding) :
        RecyclerView.ViewHolder(binding.root){


            fun bind(folder: ImageFolder){
                binding.apply {
                    folderName.text = folder.name
                    val thumbnail = this.folderImage
                        .context
                        .contentResolver
                        .loadThumbnail(folder.images.last().uri, Size(200,200),null)
                    folderImage.loadWithProgressbar(imageLoading,thumbnail)
                }
            }
        }

    companion object{
        fun createBinding(parent: ViewGroup): GalleryItemBinding {
            val inflater = LayoutInflater.from(parent.context)
            return GalleryItemBinding.inflate(inflater,parent,false)
        }
    }

}