package com.example.projectmobile

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class orderDB (
    @Expose
    @SerializedName("name_shirt") val name_shirt: String,

    @Expose
    @SerializedName("name_surname") val name_surname: String,

    @Expose
    @SerializedName("address") val address: String,

    @Expose
    @SerializedName("payment") val payment: String,

    @Expose
    @SerializedName("shipping") val shipping: String,

    @Expose
    @SerializedName("price") val price: String,

    @Expose
    @SerializedName("img_shirt") val img_shirt: String,

    @Expose
    @SerializedName("id_name") val id_name: String) {}