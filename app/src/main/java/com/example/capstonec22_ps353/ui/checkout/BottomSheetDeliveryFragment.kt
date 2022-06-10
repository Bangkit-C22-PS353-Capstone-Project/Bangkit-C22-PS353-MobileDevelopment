package com.example.capstonec22_ps353.ui.checkout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import com.example.capstonec22_ps353.databinding.FragmentBottomSheetDeliveryBinding

class BottomSheetDeliveryFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentBottomSheetDeliveryBinding? = null
    private val binding get() = _binding!!

    private lateinit var rgDelivery: RadioGroup

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBottomSheetDeliveryBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rgPengiriman.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (rgDelivery.checkedRadioButtonId) {
            // TODO:
        }
    }
}