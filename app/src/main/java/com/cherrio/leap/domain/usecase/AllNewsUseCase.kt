package com.cherrio.leap.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.cherrio.leap.data.model.Data
import com.cherrio.leap.data.model.MediaStack
import com.cherrio.leap.data.remote.Result
import com.cherrio.leap.data.remote.allnews.NewsPagingDataSource
import com.cherrio.leap.domain.repo.GetAllNewsRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AllNewsUseCase @Inject constructor(private val getAllNewsRepo: GetAllNewsRepo) {

    operator fun invoke(): Flow<PagingData<Data>> {
        return Pager(
            PagingConfig(
                pageSize = 15,
            )
        ) {
            NewsPagingDataSource(getAllNewsRepo)
        }.flow
    }


}