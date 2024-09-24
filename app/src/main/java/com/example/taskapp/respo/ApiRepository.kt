package com.example.taskapp.respo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.taskapp.helper.PostsPagingSource
import com.example.taskapp.model.PostModels
import com.example.taskapp.retrofit.ApiService
import kotlinx.coroutines.flow.Flow

class ApiRepository(private val apiService: ApiService) {

    fun getPosts(): Flow<PagingData<PostModels>> {
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = { PostsPagingSource(apiService) }
        ).flow
    }
}