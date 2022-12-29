package com.example.gamesstore.ui.home

import android.content.Context
import android.content.res.Resources
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.gamesstore.adapters.CarouselAdapter
import com.example.gamesstore.classes.Games
import com.example.gamesstore.databinding.FragmentHomeBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken
import com.google.gson.Gson

class HomeViewModel : ViewModel() {

    fun loadData(context: Context, binding: FragmentHomeBinding, view: View) {
        var url = "https://63a09058e3113e5a5c414fec.mockapi.io/api/gamestore/games"
        val stringRequest = StringRequest(Request.Method.GET, url, { response ->
            val type = object: TypeToken<ArrayList<Games>>(){}.type
            val array: ArrayList<Games> = Gson().fromJson(response, type)

            val viewPager = binding.carousel
            viewPager.adapter = CarouselAdapter(array)

            viewPager.apply {
                clipChildren = false
                clipToPadding = false
                offscreenPageLimit = 3
                (getChildAt(0) as RecyclerView).overScrollMode =
                        RecyclerView.OVER_SCROLL_NEVER
            }

            val compositePageTransformer = CompositePageTransformer()
            compositePageTransformer.addTransformer(MarginPageTransformer((40 * Resources.getSystem().displayMetrics.density).toInt()))
            viewPager.setPageTransformer(compositePageTransformer)
        }, { error ->
            Log.e("Request_error: ", error.toString())
            Snackbar.make(view, "Erro ao carregar as informações!!", Snackbar.LENGTH_LONG).show()
        })
        Volley.newRequestQueue(context).add(stringRequest)
    }
}