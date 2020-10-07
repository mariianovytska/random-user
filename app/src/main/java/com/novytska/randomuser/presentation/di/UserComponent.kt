package com.novytska.randomuser.presentation.di

import com.novytska.randomuser.presentation.ui.UserDetailsActivity
import com.novytska.randomuser.presentation.ui.UserListActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [UserModule::class]
)
interface UserComponent {
    fun inject(userListActivity: UserListActivity)
    fun inject(userDetailsActivity: UserDetailsActivity)
}