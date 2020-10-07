package com.novytska.randomuser.data.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Picture(
    @SerializedName("large") val large: String = ""
) : Serializable