package com.emretnkl.week5Assignment.data.repository

import com.emretnkl.week5Assignment.data.local.database.PostsDatabase
import com.emretnkl.week5Assignment.data.local.database.entity.PostEntity
import com.emretnkl.week5Assignment.data.remote.api.ApiService
import com.emretnkl.week5Assignment.data.model.Post
import retrofit2.Call

/**
 * Created by merttoptas on 16.10.2022.
 */

class PostRepositoryImpl constructor(
    private val apiService: ApiService,
    private val postsDatabase: PostsDatabase
) : PostRepository {
    override fun getPosts(): Call<List<Post>> {
        return apiService.getPosts()
    }

    override fun getPostById(id: Int): PostEntity? {
        return postsDatabase.postDao().getPostById(id.toString())
    }

    override fun insertFavoritePost(post: PostEntity) {
        return postsDatabase.postDao().insert(post)
    }

    override fun deleteFavoritePost(post: PostEntity) {
        return postsDatabase.postDao().delete(post)
    }
}