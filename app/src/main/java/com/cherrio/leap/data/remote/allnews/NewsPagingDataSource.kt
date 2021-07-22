package com.cherrio.leap.data.remote.allnews

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.cherrio.leap.data.model.Data
import com.cherrio.leap.domain.repo.GetAllNewsRepo
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsPagingDataSource @Inject constructor(private val getAllNewsRepo: GetAllNewsRepo): PagingSource<Int, Data>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Data> {
        return try {
            val nextPageNumber = params.key ?: INITIAL_OFFSET
            val offset = if (params.key != null) ((nextPageNumber -1) * PAGE_SIZE) + 1 else INITIAL_OFFSET
            println("Offset = $offset")
            val response = getAllNewsRepo.getNews(offset)
            val nextKey = if (response.data.isEmpty()) null else nextPageNumber + (params.loadSize / PAGE_SIZE)
            LoadResult.Page(
                data = response.data,
                prevKey = null, // Only paging forward.
                nextKey = nextKey
            )
        }catch (e: Exception){
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Data>): Int? {
        // Try to find the page key of the closest page to anchorPosition, from
        // either the prevKey or the nextKey, but you need to handle nullability
        // here:
        //  * prevKey == null -> anchorPage is the first page.
        //  * nextKey == null -> anchorPage is the last page.
        //  * both prevKey and nextKey null -> anchorPage is the initial page, so
        //    just return null.
        return null

    }

    companion object{
        private const val PAGE_SIZE = 15
        private const val INITIAL_OFFSET = 0
    }
}