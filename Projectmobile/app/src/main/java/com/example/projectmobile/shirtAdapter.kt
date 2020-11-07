package com.example.projectmobile

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_userpage.view.*
import kotlinx.android.synthetic.main.shirt_item_layout.view.*

class shirtAdapter(val item:List<shirtDB>,val context: Context): RecyclerView.Adapter <shirtAdapter.ViewHolder>(){


    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val nameText = view.nameText
        val priceText = view.priceText
        val imageshirt = view.imageshirt
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view_item : View = LayoutInflater.from(parent.context).inflate(R.layout.shirt_item_layout,parent,false)
        val myHolder = ViewHolder(view_item)

        view_item.setOnClickListener(){
            val pos = myHolder.adapterPosition
            val context:Context = parent.context
            val shrt = item[pos]
            val intent = Intent(context, shirtpage::class.java)
            intent.putExtra("mName",shrt.name_shirt)
            intent.putExtra("mPrice",shrt.price.toString())
            intent.putExtra("mDetail",shrt.detail)
            intent.putExtra("mImage",shrt.img_shirt)
            intent.putExtra("mUname",shrt.mUname)
            context.startActivity(intent)
        }
        return myHolder
    }

    override fun getItemCount(): Int {
        return item.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nameText.text = "Name: "+ item[position].name_shirt
        holder.priceText.text = "Price: "+ item[position].price.toString()
        Glide.with(context).load(item[position].img_shirt).into(holder.imageshirt)
    }


}