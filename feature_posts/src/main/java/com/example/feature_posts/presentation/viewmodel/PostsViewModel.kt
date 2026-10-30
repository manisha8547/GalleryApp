package com.example.feature_posts.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.GetPostUseCase
import com.example.domain.usecase.GetPostsDetailsUseCase
import com.example.feature_posts.presentation.state.PostsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val getPostsUseCase: GetPostUseCase
): ViewModel() {
    private val _uiState = MutableStateFlow(PostsUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadPosts()
    }

    private fun loadPosts(){
        viewModelScope.launch {

            _uiState.update {
                it.copy(loading = true)
            }
            val posts = getPostsUseCase()

            _uiState.update{
                it.copy(
                    loading = false,
                    posts = posts
                )
            }
        }
    }
}
