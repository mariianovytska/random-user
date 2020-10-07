package com.novytska.randomuser.presentation.di

import android.content.Context
import com.novytska.randomuser.data.networking.UserDao
import com.novytska.randomuser.data.repository.UserRepositoryImpl
import com.novytska.randomuser.domain.repository.UserRepository
import com.novytska.randomuser.domain.usecase.GetAllUsersUseCase
import com.novytska.randomuser.domain.usecase.GetAllUsersUseCaseImpl
import com.novytska.randomuser.presentation.utils.DateFormatter
import com.novytska.randomuser.presentation.utils.ImageLoader
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UserModule(private val context: Context) {

    @Singleton
    @Provides
    fun providesUserDao() = UserDao()

    @Provides
    @Singleton
    fun provideUserRepository(userDao: UserDao): UserRepository = UserRepositoryImpl(userDao)

    @Provides
    @Singleton
    fun provideGetAllUsersUseCase(
        userRepository: UserRepository
    ): GetAllUsersUseCase = GetAllUsersUseCaseImpl(userRepository)

    @Provides
    @Singleton
    fun providesContext(): Context {
        return context
    }

    @Provides
    @Singleton
    fun provideImageLoader(context: Context) = ImageLoader(context)

    @Provides
    @Singleton
    fun provideDateFormatter() = DateFormatter()
}