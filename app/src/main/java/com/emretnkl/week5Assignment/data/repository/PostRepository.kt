package com.emretnkl.week5Assignment.data.repository

import com.emretnkl.week5Assignment.data.local.database.entity.PostEntity
import com.emretnkl.week5Assignment.data.model.Post
import retrofit2.Call

/**
 * Created by merttoptas on 16.10.2022.
 */

interface PostRepository {
    fun getPosts(): Call<List<Post>>
    fun getPostById(id: Int): PostEntity?
    fun insertFavoritePost(post: PostEntity)
    fun deleteFavoritePost(post: PostEntity)
}