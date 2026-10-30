package com.example.domain.usecase

import com.example.domain.repository.PostRepository
import javax.inject.Inject

class GetPostUseCase @Inject constructor(
    private val postRepository: PostRepository
){
    suspend operator fun invoke() = postRepository.getPosts()
}