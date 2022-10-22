package com.emretnkl.week5Assignment.data.di

import com.emretnkl.week5Assignment.data.remote.api.UserService
import com.emretnkl.week5Assignment.data.repository.UserRepository
import com.emretnkl.week5Assignment.data.repository.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
class UsersModule {
    @Provides
    fun provideUserService(retrofit: Retrofit) : UserService {
        return retrofit.create(UserService::class.java)
    }
    @Provides
    fun provideUserRepository(userService: UserService) : UserRepository {
        return UserRepositoryImpl(userService)
    }
}