package com.example.projectmobile

import android.telecom.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import com.example.projectmobile.userDB as userDB

interface addacAPI {
    @FormUrlEncoded
    @POST("/user")
    fun insertaccount(
        @Field("id") id:String,
        @Field("password") password:String,
        @Field("name") name:String,
        @Field("surname") surname:String,
        @Field("phone") phone:String):retrofit2.Call<userDB>
}