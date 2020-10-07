package com.novytska.randomuser.presentation.ui.paged

import androidx.paging.PageKeyedDataSource
import com.novytska.randomuser.data.entity.User
import com.novytska.randomuser.domain.entity.GetAllUsersRequest
import com.novytska.randomuser.domain.usecase.GetAllUsersUseCase
import io.reactivex.disposables.CompositeDisposable

class UserDataSource(
    private val getAllUsersUseCase: GetAllUsersUseCase,
    private val results: Int,
    private val disposable: CompositeDisposable,
    private var errorHandler: ((Throwable) -> Unit)? = null
) : PageKeyedDataSource<Int, User>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, User>
    ) {

        val currentPage = 1
        val nextPage = currentPage + 1

        disposable.add(
            loadData(currentPage)
                .subscribe(
                {
                    callback.onResult(it, null, nextPage)
                },
                errorHandler
            )
        )
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, User>) {
        val currentPage = params.key
        val nextPage = currentPage + 1

        disposable.add(
            loadData(currentPage)
                .subscribe(
                    {
                        callback.onResult(it, nextPage)
                    },
                    errorHandler
                )
        )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, User>) {
        val currentPage = params.key
        val nextPage = currentPage - 1

        disposable.add(
            loadData(currentPage).subscribe(
                {
                    callback.onResult(it, nextPage)
                },
                errorHandler
            )
        )
    }

    private fun loadData(page: Int) =
        getAllUsersUseCase.execute(
            GetAllUsersRequest(results, page)
        )
}