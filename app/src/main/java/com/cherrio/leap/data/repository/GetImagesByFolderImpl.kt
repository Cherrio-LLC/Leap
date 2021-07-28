package com.cherrio.leap.data.repository

import android.app.Application
import com.cherrio.leap.data.local.gallery.GetImagesDataSource
import com.cherrio.leap.data.model.Images
import com.cherrio.leap.domain.repo.GetImagesByFolder
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetImagesByFolderImpl @Inject constructor(private val getImagesDataSource: GetImagesDataSource): GetImagesByFolder {

    override fun getImagesByFolder(application: Application): MutableMap<String, MutableList<Images>> {
        return getImagesDataSource.getImagesByFolder(application)
    }

}