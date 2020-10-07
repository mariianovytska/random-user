package com.novytska.randomuser.data.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Name(
    @SerializedName("first") val first: String = "",
    @SerializedName("last") val last: String = ""
) : Serializable {

    val fullName: String by lazy { first.plus(SPACE).plus(last) }

    companion object {
        private const val SPACE = " "
    }
}