package com.example.gamesstore.login

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.gamesstore.MainActivity
import com.example.gamesstore.classes.User
import com.example.gamesstore.databinding.ActivityLoginBinding
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken
import com.google.gson.Gson

class ActivityLoginViewModel : ViewModel() {
    val user = User()

    fun makeLogin(binding: ActivityLoginBinding, context: Context){
        user.email = binding.editUserLogin.text.toString()
        user.senha = binding.editPassLogin.text.toString()
        var sucesso = false

        val queue = Volley.newRequestQueue(context)
        val url = "https://63a09058e3113e5a5c414fec.mockapi.io/api/gamestore/user"
        val stringRequest = StringRequest(Request.Method.GET, url, { response ->
            val type = object: TypeToken<ArrayList<User>>(){}.type
            val array = Gson().fromJson<ArrayList<User>>(response, type)
            array.forEach{
                if (it.email == user.email && it.senha == user.senha) {
                    sucesso = true
                    val sharedPreferences = context.getSharedPreferences("sharedPrefs", MODE_PRIVATE)
                    sharedPreferences.edit().putInt("userId", it.id).apply()
                }
            }

            if (sucesso) {
                Toast.makeText(context, "Login realizado com sucesso!!", Toast.LENGTH_LONG).show()
                context.startActivity(Intent(context, MainActivity::class.java).setFlags(FLAG_ACTIVITY_NEW_TASK))
            } else {
                Toast.makeText(context, "Usuário/Senha inválidos!!", Toast.LENGTH_LONG).show()
            }
        }, { error ->
            Log.e("Login_error: ", error.message.toString())
            Toast.makeText(context, "Houve um erro ao tentar realizar o Login.\n" +
                    "Verifique sua conexão e tente novamente!", Toast.LENGTH_LONG).show()
        })
        queue.add(stringRequest)
    }

    fun validateInputs(context: Context, binding: ActivityLoginBinding) {
        val editLogin = binding.editUserLogin.text.toString()
        val editPass = binding.editPassLogin.text.toString()

        if (editLogin.isBlank() || editPass.isBlank()) {

        } else {
            makeLogin(binding, context)
        }
    }
}