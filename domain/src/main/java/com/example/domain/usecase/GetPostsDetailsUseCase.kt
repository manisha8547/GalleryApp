package com.example.domain.usecase

import com.example.domain.model.Post
import com.example.domain.repository.PostRepository
import javax.inject.Inject

class GetPostsDetailsUseCase @Inject constructor(
    private val postRepository: PostRepository
){
    suspend operator fun invoke(id:Int): Post{
        return postRepository.getPost(id)
    }
}