package com.example.projectmobile

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_shirtpage.*

class shirtpage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shirtpage)

        var mName = intent.getStringExtra("mName")
        var mPrice = intent.getStringExtra("mPrice")
        var mDetail = intent.getStringExtra("mDetail")
        var mImage = intent.getStringExtra("mImage")
        var mUname = intent.getStringExtra("mUname")

        Picasso.get().load(mImage).into(imageshirt)
        nameText.setText(mName)
        priceText.setText(mPrice)
        detailText.setText(mDetail)

        btn_buy.setOnClickListener() {
            val intent = Intent(this, form::class.java)
            intent.putExtra("nName",mName)
            intent.putExtra("nPrice",mPrice)
            intent.putExtra("nImage",mImage)
            intent.putExtra("nUname",mUname)
            startActivity(intent)
        }

    }

}