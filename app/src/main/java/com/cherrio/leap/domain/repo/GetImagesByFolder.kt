package com.cherrio.leap.domain.repo

import android.app.Application
import com.cherrio.leap.data.model.Images

interface GetImagesByFolder {
    fun getImagesByFolder(application: Application): MutableMap<String,MutableList<Images>>
}