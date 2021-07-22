package com.cherrio.leap.data.remote

import com.cherrio.leap.data.RemoteUtils
import com.cherrio.leap.data.model.MediaStack
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MediaStackApi {

    @GET("news?access_key=${RemoteUtils.API_KEY}&keywords=politics&countries=ng&limit=15")
    suspend fun getAllNews(
        @Query("offset") offset: Int
    ):MediaStack


}