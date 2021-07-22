package com.cherrio.leap.data.repository

import com.cherrio.leap.data.model.Data
import com.cherrio.leap.data.model.MediaStack
import com.cherrio.leap.data.remote.Result
import com.cherrio.leap.data.remote.allnews.AllNewsDataSource
import com.cherrio.leap.domain.repo.GetAllNewsRepo
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetAllNewsRepoImpl @Inject constructor(private val allNewsDataSource: AllNewsDataSource) :
    GetAllNewsRepo {

    override suspend fun getNews(offset: Int): MediaStack {
       return allNewsDataSource.getAllNewsFromNetwork(offset)
    }
}