package com.example.gamesstore.ui.registerproduct

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.gamesstore.R
import com.example.gamesstore.classes.Games
import com.example.gamesstore.databinding.FragmentRegisterProductBinding
import com.google.android.material.snackbar.Snackbar

class RegisterProductViewModel : ViewModel() {

    var games: Games = Games()

    fun registerGame(binding: FragmentRegisterProductBinding, context: Context, view: View) {
        if (validaCampos(binding, view)) {
            val queue = Volley.newRequestQueue(context)
            val url = "https://63a09058e3113e5a5c414fec.mockapi.io/api/gamestore/games"
            val stringRequest = object: StringRequest(Request.Method.POST, url, { response ->
                view.findNavController().navigate(R.id.nav_catalogo)
                Snackbar.make(view, "Sucesso ao cadastrar o produto!!", Snackbar.LENGTH_LONG)
                    .setBackgroundTint(com.google.android.material.R.attr.colorSecondaryVariant).show()
            }, { error ->
                Log.e("Register_product_error: ", error.toString())
                Snackbar.make(view, "Erro ao cadastrar o produto!!", Snackbar.LENGTH_LONG)
                    .setBackgroundTint(Color.parseColor("#ff0000")).show()
            }) {
                override fun getParams(): MutableMap<String, String>? {
                    val params = HashMap<String, String>()
                    params["titulo"] = games.titulo
                    params["preco"] = games.preco.toString()
                    params["sinopse"] = games.sinopse
                    params["capa"] = games.capa
                    return params
                }
            }
            queue.add(stringRequest)
        }
    }

    private fun validaCampos(binding: FragmentRegisterProductBinding, view: View) : Boolean {
        games.titulo = binding.editNameGame.text.toString()
        games.sinopse = binding.editSinopseGame.text.toString()
        games.preco = binding.editPriceGame.text.toString().toDouble()
        games.capa = binding.editImageGame.text.toString()

        if (games.titulo.isBlank() || games.sinopse.isBlank() || games.preco.toString().isBlank() || games.capa.isBlank()) {
            Snackbar.make(view, "Um ou mais campos estão vazios!", Snackbar.LENGTH_LONG)
                .setBackgroundTint(android.R.attr.colorSecondary).show()
            return false
        }
        return true
    }

    fun confirmCancel(context: Context, view: View) {
        AlertDialog.Builder(context)
            .setIcon(R.drawable.ic_baseline_warning)
            .setTitle("Atenção")
            .setMessage("Você tem certeza que deseja cancelar o cadastro do produto?")
            .setPositiveButton("Sim", DialogInterface.OnClickListener { dialogInterface, i ->
                view.findNavController().navigate(R.id.nav_catalogo)
            })
            .setNegativeButton("Não", DialogInterface.OnClickListener { dialogInterface, i ->
                dialogInterface.dismiss()
                view.findNavController().popBackStack()
            })
            .create()
            .show()
    }
}