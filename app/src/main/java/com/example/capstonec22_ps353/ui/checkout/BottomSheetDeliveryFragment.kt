package com.example.capstonec22_ps353.ui.checkout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.capstonec22_ps353.R
import com.example.capstonec22_ps353.databinding.FragmentBottomSheetDeliveryBinding
import com.example.capstonec22_ps353.utils.SharedViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.launch

class BottomSheetDeliveryFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentBottomSheetDeliveryBinding? = null
    private val binding get() = _binding!!
    private val sharedViewModel: SharedViewModel by activityViewModels()

//    private val args: BottomSheetDeliveryFragmentArgs by navArgs()


//    private lateinit var rdDelivery: RadioGroup

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
//        val cart = args.listCartItem
//        val qty = args.qty

        var shipment: RadioButton?
//        var ekspedisi = ""
        binding.rgPengiriman.setOnCheckedChangeListener { _, i ->
            var selected = binding.rgPengiriman.checkedRadioButtonId

            shipment = getView()?.findViewById(selected)
            ekspedisi = shipment?.text.toString()
            Toast.makeText(activity, "$i", Toast.LENGTH_SHORT).show()
        }

        lifecycleScope.launch{
            sharedViewModel.navController.observe(viewLifecycleOwner) {
                val navController = it

                binding.btnChooseShipment.setOnClickListener {
                    Toast.makeText(activity, ekspedisi, Toast.LENGTH_SHORT).show()
//                    val action = BottomSheetDeliveryFragmentDirections.actionBottomSheetDeliveryFragmentToCheckoutFragment(cart, qty,ekspedisi)
//                    navController.navigate(action)
//                    sharedViewModel.setShipment(ekspedisi)
                    findNavController().previousBackStackEntry?.savedStateHandle?.set("shipment", ekspedisi)

                    dismiss()
                }

            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}