package com.example.capstonec22_ps353.ui.checkout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.capstonec22_ps353.R
import com.example.capstonec22_ps353.databinding.FragmentCheckoutBinding
import com.example.capstonec22_ps353.preferences.PrefViewModel
import com.example.capstonec22_ps353.utils.SharedViewModel
import kotlinx.coroutines.launch
import java.text.DecimalFormat

var ekspedisi = "Pilih Pengiriman"
class CheckoutFragment : Fragment() {

    private var _binding: FragmentCheckoutBinding? = null
    private val binding get() = _binding!!

    private val prefViewModel: PrefViewModel by activityViewModels()
    private val sharedViewModel: SharedViewModel by activityViewModels()

    private val args: CheckoutFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCheckoutBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val df = DecimalFormat("#,###")
        val cart = args.listCartItem
        val qty = args.qty
//        val shipment = args.shipment
//        binding.btnShipment.text = shipment
        val dialog = BottomSheetDeliveryFragment()

        activity?.let {
            Glide.with(it)
                .load(cart.imageUrl)
                .into(binding.imgProduct)
        }

        val total = qty * cart.price

        binding.tvTitleProduct.text = cart.name
        binding.tvPrice.text = "Rp ${df.format(cart.price)}"
        binding.tvQuantity.text = "${qty}Kg"
        binding.tvTotalPesanan.text = "Total Pesanan (${qty}Kg)"
        binding.tvTotalHargaKuantitas.text = "Rp ${df.format(total)}"

//        binding.btnShipment.setOnClickListener {
//            dialog.show(childFragmentManager, "sheetDelivery")
////            binding.btnShipment.text = shipment
//        }

        val currentFragment = findNavController().getBackStackEntry(R.id.checkoutFragment)
        val dialogObserver = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME && currentFragment.savedStateHandle.contains("shipment")){
                binding.btnShipment.text = currentFragment.savedStateHandle.get("shipment")
            }
        }

        val dialogLifecycle = currentFragment.lifecycle
        dialogLifecycle.addObserver(dialogObserver)
        viewLifecycleOwner.lifecycle.addObserver(LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_DESTROY) {
                dialogLifecycle.removeObserver(dialogObserver)
            }
        })

        lifecycleScope.launch {
            sharedViewModel.navController.observe(viewLifecycleOwner) {
                val navController = it
                val callback = object : OnBackPressedCallback(true) {
                    override fun handleOnBackPressed() {
                        navController.popBackStack()
                    }
                }

                binding.btnShipment.setOnClickListener {
//                    val action = CheckoutFragmentDirections.actionCheckoutFragmentToBottomSheetDeliveryFragment(cart, qty)
                    navController.navigate(R.id.action_checkoutFragment_to_bottomSheetDeliveryFragment)
                }

                requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
                binding.btnBack.setOnClickListener {
                    navController.popBackStack()
                }
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}