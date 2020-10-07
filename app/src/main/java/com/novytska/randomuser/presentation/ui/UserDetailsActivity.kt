package com.novytska.randomuser.presentation.ui

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.novytska.randomuser.R
import com.novytska.randomuser.presentation.di.DaggerUserComponent
import com.novytska.randomuser.presentation.di.UserModule
import com.novytska.randomuser.presentation.utils.DateFormatter
import com.novytska.randomuser.presentation.utils.ImageLoader
import com.novytska.randomuser.presentation.utils.UserConst
import kotlinx.android.synthetic.main.activity_user_details.*
import javax.inject.Inject

class UserDetailsActivity : AppCompatActivity() {

    @Inject
    lateinit var imageLoader: ImageLoader

    @Inject
    lateinit var dateFormatter: DateFormatter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.title = getString(R.string.profile)

        // get passed params
        val picturePath: String? = intent.getStringExtra(UserConst.PICTURE_PATH.toString())
        val nameFull: String? = intent.getStringExtra(UserConst.FULL_NAME.toString())
        val gender: String? = intent.getStringExtra(UserConst.GENDER.toString())
        val date: String? = intent.getStringExtra(UserConst.DATE.toString())
        val cellPhone: String? = intent.getStringExtra(UserConst.CELL_PHONE.toString())
        val email: String? = intent.getStringExtra(UserConst.EMAIL.toString())

        DaggerUserComponent.builder()
            .userModule(UserModule(this))
            .build()
            .inject(this)

        initialiseData(picturePath, nameFull, gender, date, cellPhone, email)
    }

    private fun initialiseData(
        picturePath: String?,
        nameFull: String?,
        gender: String?,
        date: String?,
        cellPhone: String?,
        email: String?
    ) {
        picturePath?.let {
            user_logo_big.scaleType = ImageView.ScaleType.FIT_XY
            imageLoader.loadCircleImage(
                it,
                user_logo_big
            )
        }

        user_name.text = nameFull
        user_gender.text = gender?.capitalize()
        user_dob.text = date?.let { dateFormatter.convertFormat(it) }

        user_phone.text = cellPhone
        user_email.text = email
    }
}