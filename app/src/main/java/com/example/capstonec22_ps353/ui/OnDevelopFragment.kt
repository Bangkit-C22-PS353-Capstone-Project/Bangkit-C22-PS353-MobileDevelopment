package com.example.capstonec22_ps353.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.activityViewModels
import com.example.capstonec22_ps353.databinding.FragmentOnDevelopBinding
import com.example.capstonec22_ps353.preferences.PrefViewModel
import com.example.capstonec22_ps353.utils.SharedViewModel

class OnDevelopFragment : Fragment() {

    private val sharedViewModel: SharedViewModel by activityViewModels()

    private var _binding: FragmentOnDevelopBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnDevelopBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedViewModel.navController.observe(viewLifecycleOwner) {
            val navController = it
            val callback = object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    navController.popBackStack()
                }
            }

            requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
            binding.btnBack.setOnClickListener {
                navController.popBackStack()
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}