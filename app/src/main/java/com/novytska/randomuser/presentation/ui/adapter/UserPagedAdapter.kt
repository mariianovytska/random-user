package com.novytska.randomuser.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.novytska.randomuser.R
import com.novytska.randomuser.data.entity.User
import com.novytska.randomuser.presentation.utils.ImageLoader
import com.novytska.randomuser.presentation.utils.formatAges
import kotlinx.android.synthetic.main.item_user.view.*

open class UserPagedAdapter(
    private val imageLoader: ImageLoader,
    private val detailsSelectedListener: (User) -> Unit
) : PagedListAdapter<User, UserPagedAdapter.UserHolder>(UserDiffutilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.item_user, parent, false)
        return UserHolder(contactView)
    }

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        val itemView = holder.itemView

        getItem(position)?.apply {

            picture?.let {
                itemView.user_logo.scaleType = ImageView.ScaleType.FIT_XY
                imageLoader.loadCircleImage(
                    it.large,
                    itemView.user_logo
                )
            }

            itemView.user_name.text = name?.fullName
            itemView.user_age.text = dob?.age?.formatAges()

            itemView.setOnClickListener {
                detailsSelectedListener.invoke(this)
            }
        }
    }

    // stores and recycles views as they are scrolled off screen
    class UserHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}