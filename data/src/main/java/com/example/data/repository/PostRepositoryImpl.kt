package com.example.data.repository

import com.example.data.mapper.toDomain
import com.example.data.remote.ApiService
import com.example.domain.model.Post
import com.example.domain.repository.PostRepository
import jakarta.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val api : ApiService
): PostRepository {

    override suspend fun getPosts() = api.getPosts().map {
        it.toDomain()
    }

    override suspend fun getPost(id: Int): Post{
        return api.getPost(id).toDomain()
    }

}