package com.example.capstonec22_ps353.ui.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.capstonec22_ps353.R
import com.example.capstonec22_ps353.databinding.FragmentRegisterBinding
import com.example.capstonec22_ps353.utils.SharedViewModel
import kotlinx.coroutines.launch

class RegisterFragment : Fragment() {

    private val sharedViewModel: SharedViewModel by activityViewModels()

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnRegister.setOnClickListener {
            val tes = binding.noHpRegister.text.toString()
            Toast.makeText(activity, tes, Toast.LENGTH_SHORT).show()
        }

        lifecycleScope.launch {
            sharedViewModel.navController.observe(viewLifecycleOwner) {
                val navController = it
                val callback = object : OnBackPressedCallback(true) {
                    override fun handleOnBackPressed() {
                        TODO("Not yet implemented")
                    }
                }

                binding.btnLogin.setOnClickListener {
                    navController.navigate(R.id.action_registerFragment_to_loginFragment)
                }
                requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}