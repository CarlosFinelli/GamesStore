package com.example.gamesstore.registerscreen

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.gamesstore.login.LoginActivity
import com.example.gamesstore.classes.User
import com.example.gamesstore.databinding.ActivityRegisterBinding

class RegisterScreenViewModel : ViewModel() {
    val user = User()

    fun registerUser(binding: ActivityRegisterBinding, context: Context) {
        user.nome = binding.editName.text.toString()
        user.email = binding.editMail.text.toString()
        user.senha = binding.editPass.text.toString()
        user.genero = binding.editGender.text.toString()

        val queue = Volley.newRequestQueue(context)
        val url = "https://63a09058e3113e5a5c414fec.mockapi.io/api/gamestore/user"
        val stringRequest = object: StringRequest(Request.Method.POST, url, { response ->
            Toast.makeText(context, "Cadastro realizado com sucesso", Toast.LENGTH_LONG).show()
            context.startActivity(Intent(context, LoginActivity::class.java).setFlags(FLAG_ACTIVITY_NEW_TASK))
        }, { error ->
            Log.e("Register_error: ", error.message.toString())
            Toast.makeText(context, "Houve um erro com a sua requisição, por favor, tente novamente mais tarde", Toast.LENGTH_LONG).show()
        }) {
            override fun getParams(): MutableMap<String, String>? {
                val params = HashMap<String, String>()
                params["nome"] = user.nome
                params["email"] = user.email
                params["senha"] = user.senha
                params["genero"] = user.genero
                return params
            }
        }
        queue.add(stringRequest)
    }

    fun validateRegister(context: Context, binding: ActivityRegisterBinding) {
        val editName = binding.editName.text.toString()
        val editMail = binding.editMail.text.toString()
        val editPass = binding.editPass.text.toString()
        val editPassC = binding.editPassC.text.toString()
        val editGender = binding.editGender.text.toString()

        if (editName.isBlank() || editMail.isBlank() || editPass.isBlank()
            || editPassC.isBlank() || editGender.isBlank()) {
            Toast.makeText(context, "Um ou mais campos estão vazios", Toast.LENGTH_LONG).show()
        } else if (editPass != editPassC) {
            Toast.makeText(context, "A senha e confirmação de senha devem ser iguais", Toast.LENGTH_LONG).show()
        } else {
            registerUser(binding, context)
        }
    }
}