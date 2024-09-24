package com.example.taskapp.model

data class PostModels(

    val skip: Int,
    val total: Int,
    val body: String,
    val id: Int,
    val tags: List<String>,
    val title: String,
    val userId: Int,
    val views: Int,
    val dislikes: Int,
    val likes: Int
)