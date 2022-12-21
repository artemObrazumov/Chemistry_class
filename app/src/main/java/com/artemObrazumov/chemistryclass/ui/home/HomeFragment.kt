package com.artemObrazumov.chemistryclass.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.artemObrazumov.chemistryclass.data.repository.MainRepository
import com.artemObrazumov.chemistryclass.databinding.FragmentHomeBinding
import com.artemObrazumov.chemistryclass.ui.adapter.ReagentsAdapter
import com.artemObrazumov.chemistryclass.ui.viewModelFactory.ViewModelFactory

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var adapter: ReagentsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this,
            ViewModelFactory(MainRepository()))[HomeViewModel::class.java]
        adapter = ReagentsAdapter(emptyList()) {

        }
        binding.reagents.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.reagents.adapter = adapter

        viewModel.reagents.observe(viewLifecycleOwner) { result ->
            if (result.isSuccess) {
                adapter.setDataSet(result.getOrNull()!!)
            } else {
                Toast.makeText(requireContext(), "Не получилось", Toast.LENGTH_SHORT).show()
            }
        }
        viewModel.getReagents()
        return binding.root
    }
}