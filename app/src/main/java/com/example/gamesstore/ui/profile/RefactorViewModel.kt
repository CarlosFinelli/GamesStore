package com.example.gamesstore.ui.profile

import android.content.Context
import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.gamesstore.classes.User
import com.example.gamesstore.databinding.FragmentProfileBinding
import com.google.gson.Gson

class RefactorViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is notifications Fragment"
    }
    val text: LiveData<String> = _text

    fun getUserData(context: Context, binding: FragmentProfileBinding, view: View) {
        val url = "https://63a09058e3113e5a5c414fec.mockapi.io/api/gamestore/user/${context.getSharedPreferences("sharedPrefs",
            Context.MODE_PRIVATE
        ).getInt("userId", 0)}"
        val stringRequest = StringRequest(Request.Method.GET, url, { response ->
            val user = Gson().fromJson(response, User::class.java)
            binding.textName.text = user.nome
            binding.textMail.text = user.email
            binding.textGender.text = user.genero
        }, { error ->
            Log.e("User_error: ", error.toString())
        })
        Volley.newRequestQueue(context).add(stringRequest)
    }
}