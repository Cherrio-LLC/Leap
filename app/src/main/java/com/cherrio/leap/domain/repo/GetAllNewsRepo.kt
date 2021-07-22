package com.cherrio.leap.domain.repo

import com.cherrio.leap.data.model.Data
import com.cherrio.leap.data.model.MediaStack
import com.cherrio.leap.data.remote.Result

interface GetAllNewsRepo {
    suspend fun getNews(offset:Int): MediaStack
}