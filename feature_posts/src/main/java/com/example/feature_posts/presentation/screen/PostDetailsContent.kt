package com.example.feature_posts.presentation.screen

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.core.common.Constants
import com.example.domain.model.Post

@Composable
fun PostDetailsContent(
    post: Post,
    navController: NavController
){
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            AsyncImage(
                model = Constants.getImageUrl(post.id),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(
                modifier = Modifier.height(16.dp)
            )
            Text(
                text = post.title,
                style = MaterialTheme
                    .typography
                    .headlineSmall
            )
            Spacer(
                modifier = Modifier.height(12.dp)
            )
            Text(
                text = post.body,
                style = MaterialTheme
                    .typography
                    .bodyLarge
            )
            Spacer(
                modifier = Modifier.height(12.dp)
            )
            Text(
                text = "Post Id: ${post.id}"
            )

        }
    }
}