package com.cherrio.leap.features.allnews

import com.cherrio.leap.data.model.Data

interface CNewsAdapter {
    fun addToFavorites(data: Data): Boolean
}