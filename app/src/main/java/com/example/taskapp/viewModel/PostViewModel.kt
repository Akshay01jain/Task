package com.example.taskapp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.taskapp.helper.Resource
import com.example.taskapp.model.PostModels
import com.example.taskapp.respo.ApiRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class PostViewModel(private val repository: ApiRepository) : ViewModel() {
    val posts: Flow<PagingData<PostModels>> = repository.getPosts().cachedIn(viewModelScope)
}