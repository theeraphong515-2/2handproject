package com.example.projectmobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_registerpage.*
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    var userList  = arrayListOf<userDB>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        }
        fun register(v: View){
            val d = Intent(this, Registerpage::class.java)
            startActivity(d)
        }
        fun login(v: View){
            val intent = Intent(this, userpage::class.java)
            userList.clear()
            val api : loginAPI = Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(loginAPI :: class.java)

            api.loginUser(
                edt_id.text.toString(),
                edt_psswd.text.toString()).enqueue(object : Callback<userDB> {
                override fun onResponse(call: retrofit2.Call<userDB>, response: Response<userDB>){
                    userList.add(userDB(response.body()?.usr_id.toString(),
                        response.body()?.id.toString(),
                        response.body()?.password.toString(),
                        response.body()?.name.toString(),
                        response.body()?.surname.toString(),
                        response.body()?.phone.toString()
                    ))
                    if (response.isSuccessful()){
                        Toast.makeText(applicationContext,"Login Successfull !!", Toast.LENGTH_SHORT).show()
                        intent.putExtra("mUid",response.body()?.usr_id.toString())
                        intent.putExtra("mUname",response.body()?.name)
                        startActivity(intent)
                    }else{
                        Toast.makeText(applicationContext,"error !", Toast.LENGTH_SHORT).show()
                    }
                }
                override fun onFailure(call: retrofit2.Call<userDB>, t: Throwable){
                    Toast.makeText(applicationContext,"Incorrect !"+t.message, Toast.LENGTH_LONG).show()
                }
            })

        }
        
    }
