package com.example.taskapp.helper

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.taskapp.model.PostModels
import com.example.taskapp.retrofit.ApiService

class PostsPagingSource(
    private val apiService: ApiService
) : PagingSource<Int, PostModels>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PostModels> {
        val page = params.key ?: 0
        return try {
            val response = apiService.getPosts(skip = page * params.loadSize, limit = params.loadSize)
            LoadResult.Page(
                data = response,
                prevKey = if (page == 0) null else page - 1,
                nextKey = if (response.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PostModels>): Int? {
        return state.anchorPosition?.let { state.closestPageToPosition(it)?.prevKey?.plus(1) }
    }
}
