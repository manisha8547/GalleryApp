package com.example.feature_posts.navigation

object Routes{
    const val POSTS = "posts"
    const val DETAILS = "details/{postId}"
    fun getDetailsRoute(postId:Int): String{
        return "details/$postId"
    }
}