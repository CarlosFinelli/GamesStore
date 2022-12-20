package com.example.gamesstore.ui.dashboard

import android.app.Application
import android.content.Context
import android.content.pm.ApplicationInfo
import android.graphics.Color
import android.util.Log
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.gamesstore.R
import com.example.gamesstore.adapters.GamesAdapter
import com.example.gamesstore.classes.Games
import com.example.gamesstore.databinding.FragmentCatalogoBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken
import com.google.gson.Gson

class DashboardViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text
    lateinit var arrayList: ArrayList<Games>

    fun getGames(binding: FragmentCatalogoBinding, context: Context, view: View) {
        val url = "https://63a09058e3113e5a5c414fec.mockapi.io/api/gamestore/games"
        val queue = Volley.newRequestQueue(context)
        val stringRequest = StringRequest(Request.Method.GET, url, { response ->
            val type = object: TypeToken<ArrayList<Games>>(){}.type
            arrayList = Gson().fromJson<ArrayList<Games>>(response, type)
            binding.listGames.adapter = GamesAdapter(context, arrayList)
            binding.listGames.setOnItemClickListener { adapterView, view, i, l ->
                context.getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE).edit().putInt("productId", arrayList[i].id).apply()
                view.findNavController().navigate(R.id.nav_product)
            }
        }, { error ->
            Log.e("Request_games_error: ", error.toString())
            Snackbar.make(view, "Erro ao recuperar as informações dos jogos!!", Snackbar.LENGTH_LONG)
                .setBackgroundTint(Color.parseColor("#FF0000")).show()
        })
        queue.add(stringRequest)
    }
}