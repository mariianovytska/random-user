package com.novytska.randomuser.domain.usecase

import io.reactivex.Observable

object BaseUseCases {
    abstract class ObservableUseCase<in Params, T> : UseCase<Params, Observable<T>>()
}
