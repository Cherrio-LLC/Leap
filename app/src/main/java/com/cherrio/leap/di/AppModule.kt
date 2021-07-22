package com.cherrio.leap.di

import com.cherrio.leap.data.RemoteUtils
import com.cherrio.leap.data.remote.MediaStackApi
import com.cherrio.leap.data.repository.GetAllNewsRepoImpl
import com.cherrio.leap.domain.repo.GetAllNewsRepo
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(RemoteUtils.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideMSApi(retrofit: Retrofit):MediaStackApi =
        retrofit.create(MediaStackApi::class.java)


}