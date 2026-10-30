package com.example.feature_posts.presentation.state

import com.example.domain.model.Post

data class PostsUiState(
    val loading: Boolean = false,
    val posts: List<Post> =emptyList(),
    val error: String? = null
)