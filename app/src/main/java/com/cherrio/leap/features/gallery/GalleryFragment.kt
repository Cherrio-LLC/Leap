package com.cherrio.leap.features.gallery

import android.content.ContentResolver
import android.content.ContentUris
import android.net.Uri
import android.os.Build
import android.os.FileUtils
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.cherrio.leap.common.BaseFragment
import com.cherrio.leap.data.model.Images
import com.cherrio.leap.databinding.FragmentGalleryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GalleryFragment: BaseFragment<FragmentGalleryBinding>() {

    private val viewModel by viewModels<GalleryViewModel>()
    private val galleryAdapter = GalleryAdapter()

    override fun getBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentGalleryBinding = FragmentGalleryBinding.inflate(layoutInflater,container,false)

    override fun useViews() {
        binding.listNews.apply {
            adapter = galleryAdapter
        }
       viewModel.foldersWithImages.observe(viewLifecycleOwner){
           galleryAdapter.submitList(it)
           binding.loading.isVisible = false
       }
        requireActivity().externalMediaDirs.forEach {
            println("Media: ${it.absolutePath}")
        }

    }

}