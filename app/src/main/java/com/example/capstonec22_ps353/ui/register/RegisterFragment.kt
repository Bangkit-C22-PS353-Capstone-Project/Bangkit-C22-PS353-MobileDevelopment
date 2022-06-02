package com.example.capstonec22_ps353.ui.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.example.capstonec22_ps353.R
import com.example.capstonec22_ps353.databinding.FragmentRegisterBinding
import com.example.capstonec22_ps353.ui.SharedViewModel
import kotlinx.coroutines.launch

class RegisterFragment : Fragment() {

    private val sharedViewModel: SharedViewModel by activityViewModels()

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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
                        TODO("Not yet implemented")
                    }
                }
                requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
            }
        }
        binding.btnBack.setOnClickListener {
//            Navigation.findNavController(view).navigate(// TODO: action Register fragment to Login fragment )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}