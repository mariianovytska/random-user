package com.novytska.randomuser.presentation.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.novytska.randomuser.data.entity.User

class UserDiffutilCallback: DiffUtil.ItemCallback<User>() {

    override fun areItemsTheSame(oldItem: User, newItem: User) = oldItem.name?.fullName == newItem.name?.fullName
    override fun areContentsTheSame(oldItem: User, newItem: User) = oldItem == newItem
}