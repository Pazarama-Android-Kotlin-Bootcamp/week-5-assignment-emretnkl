package com.emretnkl.week5Assignment.data.repository

import com.emretnkl.week5Assignment.data.model.Users
import com.emretnkl.week5Assignment.data.remote.api.UserService
import retrofit2.Call

class UserRepositoryImpl constructor(
    private val userService: UserService
) : UserRepository {
    override fun getUsers(): Call<List<Users>> {
        return userService.getUsers()
    }
}