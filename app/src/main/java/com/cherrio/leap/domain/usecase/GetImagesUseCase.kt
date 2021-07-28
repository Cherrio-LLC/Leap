package com.cherrio.leap.domain.usecase

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import com.cherrio.leap.data.model.ImageFolder
import com.cherrio.leap.data.model.Images
import com.cherrio.leap.domain.repo.GetImagesByFolder
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetImagesUseCase @Inject constructor(private val getImagesByFolder: GetImagesByFolder) {
    private val folders = mutableListOf<ImageFolder>()
    @RequiresApi(Build.VERSION_CODES.N)
    operator fun invoke(application: Application): MutableList<ImageFolder>{
        val folders = mutableListOf<ImageFolder>()
        getImagesByFolder.getImagesByFolder(application).forEach { k, v ->
            folders.add(ImageFolder(k,v))
        }
        return folders
    }


}