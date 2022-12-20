package com.example.gamesstore.ui.registerproduct

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.gamesstore.R
import com.example.gamesstore.databinding.FragmentRegisterProductBinding

class RegisterProduct : Fragment() {
    lateinit var _binding: FragmentRegisterProductBinding

    companion object {
        fun newInstance() = RegisterProduct()
    }

    private lateinit var viewModel: RegisterProductViewModel


    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterProductBinding.inflate(inflater, container, false)
        val root: View = binding.root
        viewModel = ViewModelProvider(this).get(RegisterProductViewModel::class.java)

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnRegisterProduct.setOnClickListener {
            viewModel.registerGame(binding, requireContext(), requireView())
        }
        binding.btnCancel.setOnClickListener {
            viewModel.confirmCancel(requireContext(), requireView())
        }
    }
}