package com.example.feature_posts.presentation.state

import com.example.domain.model.Post

data class DetailsUiState(
    val loading: Boolean = false,
    val post: Post? = null,
    val error: String? = null
)
