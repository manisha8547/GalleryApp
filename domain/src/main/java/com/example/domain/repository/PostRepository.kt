package com.example.domain.repository

import com.example.domain.model.Post

interface PostRepository{
    suspend fun getPosts() : List<Post>
    suspend fun getPost(id:Int) : Post

}