package com.novytska.randomuser.data.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class User(
    @SerializedName("name") val name: Name? = Name(),
    @SerializedName("gender") val gender: String? = "",
    @SerializedName("picture") val picture: Picture? = Picture(),
    @SerializedName("email") val email: String? = "",
    @SerializedName("dob") val dob: Dob? = Dob(),
    @SerializedName("cell") val cell: String? = ""
) : Serializable