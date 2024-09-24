package com.example.taskapp.retrofit

import com.example.taskapp.model.PostModels
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {

    @GET("posts")
    suspend fun getPosts(
        @Query("skip") skip : Int? = null,
        @Query("limit") limit : Int? = null ) : List<PostModels>
}