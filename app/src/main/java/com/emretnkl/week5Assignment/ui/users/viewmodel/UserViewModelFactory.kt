package com.emretnkl.week5Assignment.ui.users.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.emretnkl.week5Assignment.data.repository.UserRepository

class UserViewModelFactory(private val userRepository: UserRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UsersViewModel(userRepository) as T
    }
}