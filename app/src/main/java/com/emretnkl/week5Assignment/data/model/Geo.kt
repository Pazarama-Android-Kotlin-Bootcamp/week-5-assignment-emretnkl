package com.emretnkl.week5Assignment.data.model


import com.google.gson.annotations.SerializedName

data class Geo(
    @SerializedName("lat")
    val lat: String?,
    @SerializedName("lng")
    val lng: String?
)