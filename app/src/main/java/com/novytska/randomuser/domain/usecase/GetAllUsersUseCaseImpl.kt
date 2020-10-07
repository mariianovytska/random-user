package com.novytska.randomuser.domain.usecase

import com.novytska.randomuser.data.entity.User
import com.novytska.randomuser.domain.entity.GetAllUsersRequest
import com.novytska.randomuser.domain.repository.UserRepository
import io.reactivex.Observable
import java.lang.IllegalArgumentException

class GetAllUsersUseCaseImpl(
    private val userRepository: UserRepository
) : GetAllUsersUseCase() {
    override fun build(params: GetAllUsersRequest?): Observable<List<User>> =
        params?.let {
            userRepository.getAll(it.results, it.page)
        } ?: Observable.error(IllegalArgumentException("Params can`t be null"))
}