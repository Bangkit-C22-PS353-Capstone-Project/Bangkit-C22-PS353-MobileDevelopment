package com.example.capstonec22_ps353.ui.checkout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.capstonec22_ps353.R
import com.example.capstonec22_ps353.databinding.FragmentCheckoutBinding
import com.example.capstonec22_ps353.databinding.FragmentPaymentBinding

class PaymentFragment : Fragment() {

    private var _binding : FragmentPaymentBinding?= null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPaymentBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}