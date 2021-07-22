package com.cherrio.leap.di

import com.cherrio.leap.data.repository.GetAllNewsRepoImpl
import com.cherrio.leap.domain.repo.GetAllNewsRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindAllNewsRepoInterface(getAllNewsRepoImpl: GetAllNewsRepoImpl): GetAllNewsRepo
}