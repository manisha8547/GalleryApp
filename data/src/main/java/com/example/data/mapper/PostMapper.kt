package com.example.data.mapper

import com.example.data.remote.dto.PostDto
import com.example.domain.model.Post

fun PostDto.toDomain() = Post(
    userId = userId,
    id = id,
    title = title,
    body = body,
)

