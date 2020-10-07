package com.novytska.randomuser.presentation.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.novytska.randomuser.R
import com.novytska.randomuser.data.entity.User
import com.novytska.randomuser.presentation.di.DaggerUserComponent
import com.novytska.randomuser.presentation.di.UserModule
import com.novytska.randomuser.presentation.ui.adapter.UserPagedAdapter
import com.novytska.randomuser.presentation.utils.ImageLoader
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_user_list.*
import javax.inject.Inject

class UserListActivity : AppCompatActivity() {

    @Inject
    lateinit var movieModel: UserViewModel

    @Inject
    lateinit var imageLoader: ImageLoader

    private val disposable = CompositeDisposable()
    private val onUserSelectedListener = PublishSubject.create<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)
        setSupportActionBar(findViewById(R.id.toolbar))

        DaggerUserComponent.builder()
            .userModule(UserModule(this))
            .build()
            .inject(this)

        // init toolbar
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = getString(R.string.home)

        loadData()
        initRefresh()
        initUI()
    }

    private fun initUI() {
        disposable.add(
            onUserSelectedListener.subscribe {
                //TODO
            }
        )
    }

    private fun initRefresh() {
        user_list_swipe_to_refresh.setOnRefreshListener {
            user_list_swipe_to_refresh.isRefreshing = false
            loadData()
        }
    }

    private fun loadData() {
        disposable.addAll(
            movieModel.getAll(RESULTS, disposable)
            {
                runOnUiThread {
                    Toast.makeText(this, it.localizedMessage, Toast.LENGTH_LONG).show()
                }
            }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { initAdapter(it) },
                    { Toast.makeText(this, it.localizedMessage, Toast.LENGTH_LONG).show() }
                )
        )
    }

    private fun initAdapter(users: PagedList<User>) {
        val userAdapter = UserPagedAdapter(imageLoader)
        { onUserSelectedListener.onNext(it) }

        user_list.run {
            layoutManager = LinearLayoutManager(context)
            adapter = userAdapter
        }

        userAdapter.submitList(users)
    }

    companion object {
        const val RESULTS = 20
    }
}