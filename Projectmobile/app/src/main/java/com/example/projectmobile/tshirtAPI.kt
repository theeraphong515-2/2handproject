package com.example.projectmobile

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path

interface tshirtAPI {
    @GET("allshirt")
    fun retrieveshirt(): Call<List<shirtDB>>

    companion object{
        fun create(): tshirtAPI{
            val shirtClient : tshirtAPI = Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(tshirtAPI ::class.java)
            return shirtClient
        }
    }

    @DELETE("delete/{img_shirt}")
    fun deleteshirt(
        @Path("img_shirt") img_shirt:String): Call<shirtDB>
}