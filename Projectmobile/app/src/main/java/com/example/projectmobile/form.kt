package com.example.projectmobile

import android.app.AlertDialog
import android.app.ProgressDialog.show
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.example.projectmobile.loginAPI.Companion.create
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_form.*
import kotlinx.android.synthetic.main.activity_registerpage.*
import kotlinx.android.synthetic.main.activity_shirtpage.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.gson.GsonConverterFactory.create

class form : AppCompatActivity() {
    var payment: String = ""
    var ship: String = ""
    var nName: String? = null
    var nPrice: String? = null
    var nImage: String? = null
    var nUname: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)

        val paySpinner: Spinner = spinner_payment;
        val payArray = resources.getStringArray(R.array.paymeny_array)

        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, payArray)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        paySpinner.adapter = arrayAdapter
        paySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                payment = payArray[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }


        val shipSpinner: Spinner = spinner_shipping;
        val shipArray = resources.getStringArray(R.array.shipping_array)

        val arrayAdapter1 = ArrayAdapter(this, android.R.layout.simple_spinner_item, shipArray)
        arrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        shipSpinner.adapter = arrayAdapter1
        shipSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                ship = shipArray[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }

        nName = intent.getStringExtra("nName")
        nPrice = intent.getStringExtra("nPrice")
        nImage = intent.getStringExtra("nImage")
        nUname = intent.getStringExtra("nUname")

        Picasso.get().load(nImage).into(imshirt)
        nameshirt.setText(nName)
        priceshirt.setText(nPrice)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    fun buy(view: View) {
        val intent = Intent(this, userpage::class.java)
        val myBuilder = AlertDialog.Builder(this)
        myBuilder.apply {
            setTitle("Warning")
            setMessage("คุณต้องการที่จะซื้อสินค้านี้หรือไม่?")
            setNegativeButton("Yes") { dialog, which ->
                val serv: orderAPI = Retrofit.Builder()
                    .baseUrl("http://10.0.2.2:3000/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(orderAPI::class.java)

                serv.insertorder(
                    nName.toString(),
                    edt_name.text.toString(),
                    edt_address.text.toString(),
                    payment,
                    ship,
                    nPrice.toString(),
                    nImage.toString(),
                    nUname.toString()
                ).enqueue(object : Callback<orderDB> {

                    override fun onResponse(
                        call: retrofit2.Call<orderDB>,
                        response: retrofit2.Response<orderDB>
                    ) {
                        if (response.isSuccessful) {
                            Toast.makeText(
                                applicationContext,
                                "Successfully inserted",
                                Toast.LENGTH_SHORT
                            ).show()
                            intent.putExtra("mUname",nUname)
                            startActivity(intent)
                        } else {
                            Toast.makeText(applicationContext, "Error", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: retrofit2.Call<orderDB>, t: Throwable) {
                        Toast.makeText(applicationContext, "error", Toast.LENGTH_LONG).show()

                    }
                })
                val shirtClient = tshirtAPI.create()
                shirtClient.deleteshirt(nImage.toString())
                    .enqueue(object : Callback<shirtDB> {
                        override fun onResponse(call: Call<shirtDB>, response: Response<shirtDB>) {
                            if (response.isSuccessful) {
                                Toast.makeText(applicationContext, "Successfully Deleted", Toast.LENGTH_LONG).show()
                            }
                        }
                        override fun onFailure(call: Call<shirtDB>, t: Throwable) {
                            Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                        }
                    })
            }

            setPositiveButton("No") { dialog, which -> dialog.cancel() }
            show()
        }
    }
}