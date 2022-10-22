package com.emretnkl.week5Assignment.data.remote.api

import com.emretnkl.week5Assignment.data.model.Users
import retrofit2.Call
import retrofit2.http.GET

interface UserService {
    @GET("users")
    fun getUsers(): Call<List<Users>>
}