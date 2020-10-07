package com.novytska.randomuser.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.paging.DataSource
import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import com.novytska.randomuser.data.entity.User
import com.novytska.randomuser.domain.usecase.GetAllUsersUseCase
import com.novytska.randomuser.presentation.ui.paged.UserDataSource
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class UserViewModel @Inject constructor(
    private val getAllUsersUseCase: GetAllUsersUseCase
) : ViewModel() {

    fun getAll(
        results: Int,
        disposable: CompositeDisposable,
        errorHandler: ((Throwable) -> Unit)
    ): Observable<PagedList<User>> =
        configurePagedListBuilder(results, disposable, errorHandler).buildObservable()

    private fun configurePagedListBuilder(
        results: Int,
        disposable: CompositeDisposable,
        errorHandler: ((Throwable) -> Unit)
    ): RxPagedListBuilder<Int, User> {

        val dataSourceFactory = object : DataSource.Factory<Int, User>() {

            override fun create(): DataSource<Int, User> {
                return UserDataSource(
                    getAllUsersUseCase,
                    results,
                    disposable,
                    errorHandler
                )
            }
        }

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(PAGE_SIZE)
            .build()

        return RxPagedListBuilder(dataSourceFactory, config)
    }

    companion object {
        private const val PAGE_SIZE = 20
    }
}