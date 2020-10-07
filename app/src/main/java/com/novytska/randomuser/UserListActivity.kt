package com.novytska.randomuser

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class UserListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)
        setSupportActionBar(findViewById(R.id.toolbar))
    }
}