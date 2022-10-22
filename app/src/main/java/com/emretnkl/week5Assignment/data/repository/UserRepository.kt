package com.emretnkl.week5Assignment.data.repository

import com.emretnkl.week5Assignment.data.model.Users
import retrofit2.Call

interface UserRepository {
    fun getUsers(): Call<List<Users>>

}