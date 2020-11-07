package com.example.projectmobile

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class userDB(
    @Expose
    @SerializedName("usr_id") val usr_id: String,

    @Expose
    @SerializedName("id") val id: String,

    @Expose
    @SerializedName("password") val password: String,

    @Expose
    @SerializedName("name") val name: String,

    @Expose
    @SerializedName("surname") val surname: String,

    @Expose
    @SerializedName("phone") val phone: String){}
