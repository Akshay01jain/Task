package com.example.taskapp.viewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.taskapp.respo.ApiRepository
import com.example.taskapp.viewModel.PostViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val repository: ApiRepository) : ViewModelProvider.Factory {
    //@Suppress("UNCHECKED_CAST") // Suppress the unchecked cast warning
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(PostViewModel::class.java) -> {
                PostViewModel(repository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}