package com.emretnkl.week5Assignment.data.remote.api

import androidx.room.Insert
import com.emretnkl.week5Assignment.data.model.Post
import com.emretnkl.week5Assignment.data.model.PostDTO
import com.emretnkl.week5Assignment.data.model.Users
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

/**
 * Created by merttoptas on 8.10.2022.
 */

interface ApiService {
    @GET("posts")
    fun getPosts(): Call<List<Post>>

    @GET("posts/{id}")
    fun getPostDetail(): Call<Post>


    @DELETE("posts/{id}")
    fun deletePost(@Path("{id}") id: String): Call<Post>
}
