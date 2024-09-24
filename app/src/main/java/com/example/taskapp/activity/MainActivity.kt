package com.example.taskapp.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taskapp.retrofit.ApiClient
import com.example.taskapp.helper.Status
import com.example.taskapp.R
import com.example.taskapp.adapter.PostsListAdapter
import com.example.taskapp.databinding.ActivityMainBinding
import com.example.taskapp.respo.ApiRepository
import com.example.taskapp.viewModel.PostViewModel
import com.example.taskapp.viewModelFactory.ViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: PostsListAdapter
    private val viewModel: PostViewModel by viewModels { ViewModelFactory(ApiRepository(ApiClient.apiServices)) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        adapter = PostsListAdapter()
        binding.recycleView.layoutManager = LinearLayoutManager(this)
        binding.recycleView.adapter = adapter

        // Use lifecycleScope to collect the flow from ViewModel
        lifecycleScope.launch {
            viewModel.posts.collectLatest { pagingData ->
                adapter.submitData(pagingData)
            }
        }
    }
}