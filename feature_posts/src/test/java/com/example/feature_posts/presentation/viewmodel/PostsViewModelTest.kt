package com.example.feature_posts.presentation.viewmodel

import com.example.domain.model.Post
import com.example.domain.usecase.GetPostUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Rule
import org.junit.Test
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk

@OptIn(ExperimentalCoroutinesApi::class)
class PostsViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun `loads posts and updates ui state`() = runTest(mainDispatcherRule.dispatcher) {
        val posts = listOf(
            Post(userId = 1, id = 1, title = "First post", body = "First body"),
            Post(userId = 2, id = 2, title = "Second post", body = "Second body")
        )
        val getPostsUseCase = mockk<GetPostUseCase>()
        coEvery { getPostsUseCase() } returns posts

        val viewModel = PostsViewModel(getPostsUseCase)
        advanceUntilIdle()

        assertFalse(viewModel.uiState.value.loading)
        assertEquals(posts, viewModel.uiState.value.posts)
        assertNull(viewModel.uiState.value.error)
        coVerify(exactly = 1) { getPostsUseCase() }
    }
}
