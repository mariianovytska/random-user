package com.novytska.randomuser.data.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Dob(
    @SerializedName("date") val date: String = "",
    @SerializedName("age") val age: String = ""
) : Serializable