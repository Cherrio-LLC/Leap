package com.cherrio.leap.data.remote.allnews

import com.cherrio.leap.data.model.MediaStack
import com.cherrio.leap.di.AppModule
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AllNewsDataSource @Inject constructor(){

    suspend fun getAllNewsFromNetwork(offset: Int): MediaStack {
        val mediaStack = AppModule.provideMSApi(AppModule.provideRetrofit())
        return mediaStack.getAllNews(offset)
    }


}