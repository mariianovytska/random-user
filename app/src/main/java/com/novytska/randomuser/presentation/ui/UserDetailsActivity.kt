package com.novytska.randomuser.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.novytska.randomuser.R

class UserDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)
        setSupportActionBar(findViewById(R.id.toolbar))

    }
}