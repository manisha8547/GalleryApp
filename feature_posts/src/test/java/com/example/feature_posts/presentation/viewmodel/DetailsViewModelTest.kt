package com.example.feature_posts.presentation.viewmodel

import com.example.domain.model.Post
import com.example.domain.usecase.GetPostsDetailsUseCase
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk

@OptIn(ExperimentalCoroutinesApi::class)
class DetailsViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun `loads post and updates ui state`() = runTest(mainDispatcherRule.dispatcher) {
        val postId = 7
        val post = Post(userId = 1, id = postId, title = "A post", body = "Post body")
        val getPostsDetailsUseCase = mockk<GetPostsDetailsUseCase>()
        coEvery { getPostsDetailsUseCase(postId) } returns post

        val viewModel = DetailsViewModel(getPostsDetailsUseCase)
        viewModel.loadPost(postId)
        advanceUntilIdle()

        assertFalse(viewModel.uiState.value.loading)
        assertEquals(post, viewModel.uiState.value.post)
        assertNull(viewModel.uiState.value.error)
        coVerify(exactly = 1) { getPostsDetailsUseCase(postId) }
    }

    @Test
    fun `shows loading until post request completes`() = runTest(mainDispatcherRule.dispatcher) {
        val postId = 7
        val post = Post(userId = 1, id = postId, title = "A post", body = "Post body")
        val result = CompletableDeferred<Post>()
        val getPostsDetailsUseCase = mockk<GetPostsDetailsUseCase>()
        coEvery { getPostsDetailsUseCase(postId) } coAnswers { result.await() }

        val viewModel = DetailsViewModel(getPostsDetailsUseCase)
        viewModel.loadPost(postId)
        runCurrent()

        assertTrue(viewModel.uiState.value.loading)

        result.complete(post)
        advanceUntilIdle()

        assertFalse(viewModel.uiState.value.loading)
        assertEquals(post, viewModel.uiState.value.post)
    }

    @Test
    fun `sets error when loading post fails`() = runTest(mainDispatcherRule.dispatcher) {
        val postId = 7
        val errorMessage = "Unable to load post"
        val getPostsDetailsUseCase = mockk<GetPostsDetailsUseCase>()
        coEvery { getPostsDetailsUseCase(postId) } throws IllegalStateException(errorMessage)

        val viewModel = DetailsViewModel(getPostsDetailsUseCase)
        viewModel.loadPost(postId)
        advanceUntilIdle()

        assertFalse(viewModel.uiState.value.loading)
        assertNull(viewModel.uiState.value.post)
        assertEquals(errorMessage, viewModel.uiState.value.error)
        coVerify(exactly = 1) { getPostsDetailsUseCase(postId) }
    }
}
