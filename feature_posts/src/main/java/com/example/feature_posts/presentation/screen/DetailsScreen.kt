package com.example.feature_posts.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.keepScreenOn
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.feature_posts.presentation.viewmodel.DetailsViewModel

@Composable
fun DetailScreen(postId: Int, navController: NavHostController, viewModel: DetailsViewModel = hiltViewModel()){

   val state by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(postId) {
        viewModel.loadPost(postId)
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ){
        when{
            state.loading ->{
                CircularProgressIndicator(
                    modifier = Modifier.align(
                        Alignment.Center
                    )
                )
            }
            state.error !=null -> {
                Text(
                    text = state.error ?: "",
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            state.post != null -> {
                PostDetailsContent(
                    post = state.post!!,
                    navController = navController
                )
            }
        }
    }


}