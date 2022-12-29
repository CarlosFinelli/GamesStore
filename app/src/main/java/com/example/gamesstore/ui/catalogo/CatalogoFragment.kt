package com.example.gamesstore.ui.catalogo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.gamesstore.R
import com.example.gamesstore.adapters.GamesAdapter
import com.example.gamesstore.classes.Games
import com.example.gamesstore.databinding.FragmentCatalogoBinding

class CatalogoFragment : Fragment() {

    private var _binding: FragmentCatalogoBinding? = null
    lateinit var adapter: GamesAdapter
    lateinit var listGames: GridView
    lateinit var viewModel: CatalogoViewModel
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
            ViewModelProvider(this).get(CatalogoViewModel::class.java)
        viewModel = ViewModelProvider(this).get(CatalogoViewModel::class.java)

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