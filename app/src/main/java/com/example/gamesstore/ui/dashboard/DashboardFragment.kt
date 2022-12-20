package com.example.gamesstore.ui.dashboard

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import android.widget.ListView
import android.widget.TextView
import androidx.core.os.HandlerCompat.postDelayed
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.gamesstore.MainActivity
import com.example.gamesstore.R
import com.example.gamesstore.adapters.GamesAdapter
import com.example.gamesstore.classes.Games
import com.example.gamesstore.databinding.FragmentCatalogoBinding
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken
import com.google.gson.Gson

class DashboardFragment : Fragment() {

    private var _binding: FragmentCatalogoBinding? = null
    lateinit var adapter: GamesAdapter
    lateinit var listGames: GridView
    lateinit var viewModel: DashboardViewModel
    var arrayList = ArrayList<Games>()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)
        viewModel = ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentCatalogoBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listGames = binding.listGames
        viewModel.getGames(binding, requireContext(), requireView())
        binding.btnAdd.setOnClickListener {
            findNavController().navigate(R.id.nav_register_product)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}