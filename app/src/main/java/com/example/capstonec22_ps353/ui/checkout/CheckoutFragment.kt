package com.example.capstonec22_ps353.ui.checkout

import android.app.AlertDialog
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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

//var ekspedisi = "Pilih Pengiriman"
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

        val shipmentCost = resources.getIntArray(R.array.dummy_shipment_cost)




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

        val currentFragment = findNavController().getBackStackEntry(R.id.checkoutFragment)
        val dialogObserver = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME && currentFragment.savedStateHandle.contains("shipment")) {
                binding.btnShipment.text = currentFragment.savedStateHandle.get("shipment")
                val idShipment = binding.btnShipment.text.toString()
                when (idShipment) {
                    SICEPAT -> {
                        val totalPembayaran = total + shipmentCost[0]
                        binding.tvTotalHargaPengiriman.text = "Rp ${df.format(shipmentCost[0])}"
                        binding.tvTotalHargaPembayaran.text = "Rp ${df.format(totalPembayaran)}"
                    }
                    IDEXPRESS -> {
                        val totalPembayaran = total + shipmentCost[1]
                        binding.tvTotalHargaPengiriman.text = "Rp ${df.format(shipmentCost[1])}"
                        binding.tvTotalHargaPembayaran.text = "Rp ${df.format(totalPembayaran)}"
                    }
                    JNE -> {
                        val totalPembayaran = total + shipmentCost[2]
                        binding.tvTotalHargaPengiriman.text = "Rp ${df.format(shipmentCost[2])}"
                        binding.tvTotalHargaPembayaran.text = "Rp ${df.format(totalPembayaran)}"
                    }
                    TIKI -> {
                        val totalPembayaran = total + shipmentCost[3]
                        binding.tvTotalHargaPengiriman.text = "Rp ${df.format(shipmentCost[3])}"
                        binding.tvTotalHargaPembayaran.text = "Rp ${df.format(totalPembayaran)}"
                    }
                    JNT -> {
                        val totalPembayaran = total + shipmentCost[4]
                        binding.tvTotalHargaPengiriman.text = "Rp ${df.format(shipmentCost[4])}"
                        binding.tvTotalHargaPembayaran.text = "Rp ${df.format(totalPembayaran)}"
                    }
                }

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

                prefViewModel.getInfo().observe(viewLifecycleOwner) { user ->
                    if (user.address == "") {
                        binding.tvFullAddress.setOnClickListener {
                            navController.navigate(R.id.action_checkoutFragment_to_editProfileFragment)
                        }
                        binding.tvFullAddress.gravity = Gravity.CENTER
                        binding.tvFullAddress.textSize = 16f

                    } else {
                        binding.tvFullAddress.isEnabled = false
                        binding.tvFullAddress.text =
                            "${user.username} | (+62) ${user.noHp}\n${user.address}"
                    }
                }

                binding.btnNextPayment.setOnClickListener {
                    val total = binding.tvTotalHargaPembayaran.text.toString()
                    val priceShipment = binding.tvTotalHargaPengiriman.text.toString()
                    val action =
                        CheckoutFragmentDirections.actionCheckoutFragmentToPaymentFragment(total)

                    if (priceShipment == "Rp 0") {
                        Toast.makeText(
                            activity,
                            "Anda harus memilih pengiriman",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        AlertDialog.Builder(activity).apply {
                            setTitle("Tunggu Sebentar!!")
                            setMessage("Apakah anda sudah memeriksa pesanan anda kembali?")
                            setPositiveButton("Ya") { _, _ ->
                                navController.navigate(action)
                            }

                            setNegativeButton("Tidak") { _, _ -> }
                            create()
                            show()
                        }

                    }

                }

                binding.btnShipment.setOnClickListener {
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

    companion object {
        const val SICEPAT = "Si Cepat (Rp 70.000)"
        const val IDEXPRESS = "ID Express (Rp 65.000)"
        const val JNE = "JNE (Rp 80.000)"
        const val TIKI = "TIKI (Rp 60.000)"
        const val JNT = "JNT (Rp 90.000)"
    }

}