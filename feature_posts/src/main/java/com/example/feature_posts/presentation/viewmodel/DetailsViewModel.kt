package com.example.feature_posts.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.util.copy
import com.example.domain.usecase.GetPostUseCase
import com.example.domain.usecase.GetPostsDetailsUseCase
import com.example.feature_posts.presentation.state.DetailsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getPostsDetailsUseCase: GetPostsDetailsUseCase
): ViewModel()  {
    private val _uiState = MutableStateFlow(DetailsUiState())
    val uiState = _uiState.asStateFlow()

    fun loadPost(postId : Int){
        viewModelScope.launch {
            _uiState.update {
                it.copy(loading = true)
            }
            try {
                val post = getPostsDetailsUseCase(postId)
                _uiState.update { it.copy(loading = false, post=post) }
            }catch (e: Exception){
                _uiState.update {
                    it.copy(loading = false, error = e.message)
                }
            }
        }
    }
}