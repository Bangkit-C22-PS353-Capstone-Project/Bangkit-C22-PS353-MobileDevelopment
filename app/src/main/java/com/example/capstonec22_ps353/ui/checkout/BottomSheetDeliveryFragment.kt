package com.example.capstonec22_ps353.ui.checkout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
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


        val sicepat = binding.rbSicepat.text.toString()
        val idexpress = binding.rbIdExpress.text.toString()
        val jne = binding.rbJne.text.toString()
//        val tiki = binding.


//        rbSicepat.text = "Si Cepat (Rp ${shipmentCost[0]})"
//        rbIdExpress.text = "ID Express (Rp ${shipmentCost[1]})"
//        rbJne.text = "JNE (Rp ${shipmentCost[2]})"
//        rbTiki.text = "TIKI (Rp ${shipmentCost[3]})"
//        rbJnt.text = "JNE (Rp ${shipmentCost[4]})"
        var ekspedisi = ""
        var selected = 0
        binding.rgPengiriman.setOnCheckedChangeListener { radio, i ->
            selected = i
            shipment = getView()?.findViewById(i)
            ekspedisi = shipment?.text.toString()
//            Toast.makeText(activity, "$i", Toast.LENGTH_SHORT).show()
        }

//        binding.apply {
//            rbSicepat.text = "Si Cepat (Rp ${shipmentCost[0]})"
//            rbIdExpress.text = "ID Express (Rp ${shipmentCost[1]})"
//            rbJne.text = "JNE (Rp ${shipmentCost[2]})"
//            rbTiki.text = "TIKI (Rp ${shipmentCost[3]})"
//            rbJnt.text = "JNT (Rp ${shipmentCost[4]})"
//        }

        binding.btnChooseShipment.setOnClickListener {
//            Toast.makeText(activity, "$selected", Toast.LENGTH_SHORT).show()
            findNavController().previousBackStackEntry?.savedStateHandle?.set(
                "shipment",ekspedisi
            )
            dismiss()
        }



        lifecycleScope.launch {
            sharedViewModel.navController.observe(viewLifecycleOwner) {
                val navController = it



            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}