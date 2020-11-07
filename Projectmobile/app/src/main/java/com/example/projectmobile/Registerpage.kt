package com.example.projectmobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telecom.Call
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.btn_register
import kotlinx.android.synthetic.main.activity_registerpage.*
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.CacheResponse

class Registerpage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registerpage)
        bt_reset.setOnClickListener() {
            IdAccount.text.clear()
            password.text.clear()
            name.text.clear()
            surname.text.clear()
            phone.text.clear()

        }
    }
    fun add(view: View){
            val serv : addacAPI = Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(addacAPI::class.java)

            serv.insertaccount(
                IdAccount.text.toString(),
                password.text.toString(),
                name.text.toString(),
                surname.text.toString(),
                phone.text.toString()).enqueue(object : Callback<userDB>{

                override fun onResponse(call: retrofit2.Call<userDB>, response: retrofit2.Response<userDB>) {
                    if (response.isSuccessful) {
                        Toast.makeText(applicationContext, "Successfully inserted", Toast.LENGTH_SHORT).show()
                        finish()
                    } else {
                        Toast.makeText(applicationContext, "Error", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: retrofit2.Call<userDB>, t: Throwable) {
                    Toast.makeText(applicationContext, "Successfully inserted", Toast.LENGTH_LONG).show()

                }
            })
        }

}