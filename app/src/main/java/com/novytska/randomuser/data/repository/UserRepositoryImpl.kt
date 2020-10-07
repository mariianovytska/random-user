package com.novytska.randomuser.data.repository

import com.novytska.randomuser.data.entity.User
import com.novytska.randomuser.data.networking.UserDao
import com.novytska.randomuser.domain.repository.UserRepository
import io.reactivex.Observable

class UserRepositoryImpl(
    private val userDao: UserDao
) : UserRepository {

    override fun getAll(results: Int, page: Int): Observable<List<User>> =
        userDao.getAll(results, page).map { it.results }
}
