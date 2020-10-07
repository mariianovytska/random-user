package com.novytska.randomuser.data.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CloudResponse<T>(
    @SerializedName("results") val results: List<T> = listOf()
) : Serializable