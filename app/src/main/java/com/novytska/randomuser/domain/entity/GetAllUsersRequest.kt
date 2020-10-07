package com.novytska.randomuser.domain.entity

data class GetAllUsersRequest(
    val results: Int,
    val page: Int
)