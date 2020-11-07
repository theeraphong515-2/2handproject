package com.example.projectmobile

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface loginAPI {
    @FormUrlEncoded
    @POST("login")
    fun loginUser(
        @Field("id")username: String,
        @Field("password")password: String): Call<userDB>

    companion object{
        fun create(): loginAPI{
            val userClient : loginAPI = Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(loginAPI ::class.java)
            return userClient
        }
    }
}