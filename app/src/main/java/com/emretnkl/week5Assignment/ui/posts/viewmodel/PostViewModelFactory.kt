package com.emretnkl.week5Assignment.ui.posts.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.emretnkl.week5Assignment.data.repository.PostRepository

/**
 * Created by merttoptas on 16.10.2022.
 */

@Deprecated("Use ViewModelFactory from Hilt")
class PostViewModelFactory(private val postRepository: PostRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PostsViewModel(postRepository) as T
    }
}