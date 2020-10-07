package com.novytska.randomuser.domain.repository

import com.novytska.randomuser.data.entity.User
import io.reactivex.Observable

interface UserRepository {

    /**
     * Fetch users
     * @param results - number of generated users in one request (up to 5,000)
     * @param page - page number
     * @return observable response
     */
    fun getAll(results: Int, page: Int): Observable<List<User>>
}