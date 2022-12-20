package com.example.gamesstore.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gamesstore.R
import com.example.gamesstore.classes.Games
import com.squareup.picasso.Picasso
import java.text.DecimalFormat

class GamesAdapter(private val context: Context, private val arrayList: ArrayList<Games>) :
BaseAdapter() {


    override fun getCount(): Int {
        return arrayList.size
    }

    override fun getItem(p0: Int): Any {
        return arrayList[p0]
    }

    override fun getItemId(p0: Int): Long {
        return arrayList[p0].id.toLong()
    }

    @SuppressLint("ViewHolder", "SetTextI18n")
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val convertView = LayoutInflater.from(context).inflate(R.layout.adapter_games, p2, false)
        convertView.findViewById<TextView>(R.id.text_title).text = arrayList[p0].titulo
        convertView.findViewById<TextView>(R.id.text_price).text = "R$ ${arrayList[p0].preco}"
        val imageCape = convertView.findViewById<ImageView>(R.id.game_cape)
        Picasso.get().load(arrayList[p0].capa).fit().into(imageCape)
        return convertView
    }

}