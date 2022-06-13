package com.example.capstonec22_ps353.ui.payment

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.capstonec22_ps353.databinding.FragmentPaymentBinding
import com.example.capstonec22_ps353.preferences.PrefViewModel
import com.example.capstonec22_ps353.utils.SharedViewModel
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

class PaymentFragment : Fragment() {

    private var _binding : FragmentPaymentBinding?= null
    private val binding get() = _binding!!

    private val prefViewModel: PrefViewModel by activityViewModels()
    private val sharedViewModel: SharedViewModel by activityViewModels()

    private val args: PaymentFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPaymentBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val df = DecimalFormat("#,###")
        val total = args.totalPayment
        binding.tvTotalPrice.setText(total)

        val calendar = Calendar.getInstance()
        val simpleDateFormat = SimpleDateFormat("dd-MM-yyy")
        val date = simpleDateFormat.format(calendar.time)

        binding.tvPaymentTime.text = "Mohon bayar sebelum\n$date\njam 23.59 WIB"

        binding.apply {
            btnCopy.setOnClickListener {
                var clipBoard  = activity?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val noRek = tvBankAccount.text.toString()
                var clip = ClipData.newPlainText("Copied Text", noRek)
                clipBoard.setPrimaryClip(clip)
//                clipBoard.primaryClip = clip
                Toast.makeText(activity, "Copied Text", Toast.LENGTH_SHORT).show()


            }
        }

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