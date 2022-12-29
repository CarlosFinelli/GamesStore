package com.example.gamesstore.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.gamesstore.R
import com.example.gamesstore.classes.Games
import com.squareup.picasso.Picasso

class CarouselAdapter(private val arrayList: ArrayList<Games>) :
RecyclerView.Adapter<CarouselAdapter.CarouselItemViewHolder>() {

    class CarouselItemViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun  onCreateViewHolder(parent: ViewGroup, viewType: Int) : CarouselItemViewHolder {
        return CarouselItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_carousel, parent, false))
    }

    override fun onBindViewHolder(holder: CarouselItemViewHolder, position: Int) {
        val carouselImage = holder.itemView.findViewById<ImageView>(R.id.imageCarousel)
        Picasso.get().load(arrayList[position].capa).fit().into(carouselImage)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }
}