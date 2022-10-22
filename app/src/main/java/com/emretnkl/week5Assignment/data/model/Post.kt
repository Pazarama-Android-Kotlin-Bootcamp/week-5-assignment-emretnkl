package com.emretnkl.week5Assignment.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName


data class Post(
    @SerializedName("body")
    val body: String?,
    @SerializedName("userId")
    val userId: Int?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("title")
    val title: String?
)

data class PostDTO(
    val body: String?,
    val userId: Int?,
    val id: Int?,
    val title: String?,
    val isFavorite: Boolean = false
) : java.io.Serializable {
    fun toJson():String{
        return Gson().toJson(this)
    }
    companion object {
        fun fromJson(jsonValue:String) : PostDTO {
            return Gson().fromJson(jsonValue,PostDTO::class.java)
        }
    }

}