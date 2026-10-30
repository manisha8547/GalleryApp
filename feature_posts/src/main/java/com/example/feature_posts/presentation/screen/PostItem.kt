package com.example.feature_posts.presentation.screen

import android.R
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.domain.model.Post
import androidx.compose.ui.layout.ContentScale
import com.example.core.common.Constants

@Composable
fun PostItem(
    post: Post,
    onItemClick: () -> Unit
){
    Card(
        modifier = Modifier
        .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp)
        .clickable{
            onItemClick()
        },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ){
        Column{
            AsyncImage(
                model = Constants.getImageUrl(post.id),
                contentDescription = "Post Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = post.title,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 2,
                )

                Spacer(
                    modifier = Modifier.height(8.dp)
                )



            }

        }
    }
}