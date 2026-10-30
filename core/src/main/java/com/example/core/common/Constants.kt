package com.example.core.common

object Constants {
    const val BASE_URL = "https://jsonPlaceholder.typicode.com/"
    const val IMAGE_URL = "https://picsum.photos/300/300?random="

    fun getImageUrl(id: Int): String{
        return IMAGE_URL +id
    }
}