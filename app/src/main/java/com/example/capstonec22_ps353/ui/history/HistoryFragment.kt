package com.example.capstonec22_ps353.ui.history

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.capstonec22_ps353.databinding.FragmentHistoryBinding
import com.example.capstonec22_ps353.utils.SharedViewModel
import kotlinx.coroutines.launch

class HistoryFragment : Fragment() {

    private lateinit var rvHistory: RecyclerView

    private val sharedViewModel: SharedViewModel by activityViewModels()

    private var _binding : FragmentHistoryBinding?=null
    private val binding get() = _binding!!

//    private lateinit var listHistoryAdapter: ListHistoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupActionButton()
        showRecyclerList()
    }

    private fun setupActionButton() {
        lifecycleScope.launch {
            sharedViewModel.navController.observe(viewLifecycleOwner) {

            }
        }
    }

    private fun showRecyclerList() {
        rvHistory.layoutManager = LinearLayoutManager(context)
        rvHistory.setHasFixedSize(true)
//        rvHistory.adapter = listHistoryAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}