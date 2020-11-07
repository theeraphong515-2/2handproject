package com.example.projectmobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.get
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_shirtpage.*
import kotlinx.android.synthetic.main.activity_userpage.*
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class userpage : AppCompatActivity() {
    var shirtList = arrayListOf<shirtDB>()
    var mUname: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_userpage)

        mUname = intent.getStringExtra("mUname")
        unameText.setText(mUname)

        recycler_view.adapter = shirtAdapter(this.shirtList,applicationContext)
        recycler_view.layoutManager = GridLayoutManager(this,2)
        recycler_view.addItemDecoration(DividerItemDecoration(recycler_view.getContext(),DividerItemDecoration.VERTICAL))

    }

    override fun onResume() {
        super.onResume()
        callshirtdata() }

    fun callshirtdata(){
        shirtList.clear();
        val serv : tshirtAPI = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(tshirtAPI ::class.java)

        serv.retrieveshirt()
            .enqueue(object : Callback<List<shirtDB>>{

                override fun onResponse(call: Call<List<shirtDB>>, response: Response<List<shirtDB>>) {
                    response.body()?.forEach{
                        shirtList.add(shirtDB(it.name_shirt,it.price,it.detail,it.img_shirt,mUname.toString())) }
                    recycler_view.adapter = shirtAdapter(shirtList,applicationContext)
                }

                override fun onFailure(call: Call<List<shirtDB>>, t: Throwable) = t.printStackTrace()
            }) }
    }
