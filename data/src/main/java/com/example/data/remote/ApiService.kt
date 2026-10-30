package com.example.data.remote

import com.example.data.remote.dto.PostDto
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService{
    @GET("posts")
    suspend fun getPosts(): List<PostDto>

    @GET("posts/{id}")
    suspend fun getPost(
        @Path("id") id: Int
    ) : PostDto

}