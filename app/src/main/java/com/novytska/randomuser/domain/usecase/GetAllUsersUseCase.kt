package com.novytska.randomuser.domain.usecase

import com.novytska.randomuser.data.entity.User
import com.novytska.randomuser.domain.entity.GetAllUsersRequest

abstract class GetAllUsersUseCase : BaseUseCases.ObservableUseCase<GetAllUsersRequest, List<User>>()