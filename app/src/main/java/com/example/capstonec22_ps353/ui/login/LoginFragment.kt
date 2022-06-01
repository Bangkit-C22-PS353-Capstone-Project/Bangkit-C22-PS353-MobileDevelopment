package com.example.capstonec22_ps353.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.capstonec22_ps353.R
import com.example.capstonec22_ps353.databinding.FragmentLoginBinding
import com.example.capstonec22_ps353.ui.SharedViewModel
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {

    private val sharedViewModel: SharedViewModel by activityViewModels()

    private var _binding: FragmentLoginBinding?=null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        lifecycleScope.launch {
            sharedViewModel.navController.observe(viewLifecycleOwner) {
                val navController = it
                val callback = object : OnBackPressedCallback(true) {
                    override fun handleOnBackPressed() {
                        navController.navigate(R.id.action_loginFragment_to_mainFragment)
                    }
                }
                requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,callback)
                binding.btnBack.setOnClickListener {
                    navController.navigate(R.id.action_loginFragment_to_mainFragment)
                }
            }
        }
//        binding.tvback.setOnClickListener {
////            Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_homeFragment)
//        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}