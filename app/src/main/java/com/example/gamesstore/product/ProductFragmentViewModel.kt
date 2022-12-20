package com.example.gamesstore.product

import android.content.Context
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.gamesstore.classes.Games
import com.example.gamesstore.databinding.FragmentProductBinding
import com.google.android.material.R
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.squareup.picasso.Picasso

class ProductFragmentViewModel : ViewModel() {

    fun getDataGame(context: Context, binding: FragmentProductBinding, view: View) {
        val url = "https://63a09058e3113e5a5c414fec.mockapi.io/api/gamestore/games/${context.getSharedPreferences("sharedPrefs",
            Context.MODE_PRIVATE
        ).getInt("productId", 0)}"
        val stringRequest = StringRequest(Request.Method.GET, url, { response ->
            val games = Gson().fromJson(response, Games::class.java)
            binding.gameSinopse.text = games.sinopse
            binding.gameTitle.text = games.titulo
            binding.gamePrice.text = "R$ ${games.preco.toString()}"
            Picasso.get().load(games.capa).fit().into(binding.gameCape)
        }, { error ->
            Log.e("Product_error: ", error.toString())
            Snackbar.make(view, "Houve um erro ao recuperar as informações do produto!!", Snackbar.LENGTH_LONG)
                .setBackgroundTint(R.attr.colorSecondaryVariant).show()
        })
        Volley.newRequestQueue(context).add(stringRequest)
    }
}