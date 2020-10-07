package com.novytska.randomuser.presentation.ui

import android.content.Intent
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
import com.novytska.randomuser.presentation.utils.UserConst
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
        supportActionBar?.title = getString(R.string.home)

        DaggerUserComponent.builder()
            .userModule(UserModule(this))
            .build()
            .inject(this)

        supportActionBar?.title = getString(R.string.home)

        loadData()
        initRefresh()
        initUI()
    }

    private fun initUI() {
        disposable.add(
            onUserSelectedListener.subscribe { navigateToUserDetails(it) }
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

    private fun navigateToUserDetails(user: User) {
        val intent = Intent(this, UserDetailsActivity::class.java)
        intent.putExtra(UserConst.PICTURE_PATH.toString(), user.picture?.large)
        intent.putExtra(UserConst.FULL_NAME.toString(), user.name?.fullName)
        intent.putExtra(UserConst.GENDER.toString(), user.gender)
        intent.putExtra(UserConst.DATE.toString(), user.dob?.date)
        intent.putExtra(UserConst.CELL_PHONE.toString(), user.cell)
        intent.putExtra(UserConst.EMAIL.toString(), user.email)
        startActivity(intent)
    }

    companion object {
        const val RESULTS = 20
    }
}