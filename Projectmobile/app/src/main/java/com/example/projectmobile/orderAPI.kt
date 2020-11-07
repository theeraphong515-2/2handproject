package com.example.projectmobile

import retrofit2.Call
import retrofit2.http.*

interface orderAPI {
    @FormUrlEncoded
    @POST("/ordr")
    fun insertorder(
        @Field("name_shirt") name_shirt:String,
        @Field("name_surname") name_surname:String,
        @Field("address") address:String,
        @Field("payment") payment:String,
        @Field("shipping") shipping:String,
        @Field("price") price:String,
        @Field("img_shirt") img_shirt:String,
        @Field("id_name") id_name:String):retrofit2.Call<orderDB>

}