package com.example.feature_posts.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.feature_posts.presentation.screen.DetailScreen
import com.example.feature_posts.presentation.screen.PostsScreen

@Composable
fun PostNavGraph(){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.POSTS
    ) {
        composable(
            route= Routes.POSTS
        ){
            PostsScreen(
                navController  = navController
            )
        }

        composable(
            route = Routes.DETAILS,
            arguments = listOf(
                navArgument("postId"){
                    type = NavType.IntType
                }
            )
        ){
            backStackEntry ->
            val postId = backStackEntry.arguments?.getInt("postId")?:0
            DetailScreen(postId = postId, navController = navController)
        }
    }
}