package com.example.capstonec22_ps353.ui.cart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.capstonec22_ps353.databinding.FragmentCartBinding
import com.example.capstonec22_ps353.utils.SharedViewModel
import kotlinx.coroutines.launch

class CartFragment : Fragment() {

    private val sharedViewModel: SharedViewModel by activityViewModels()

    private var _binding: FragmentCartBinding?=null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return (binding.root)
    }

    /**
     todo: dlan gua ga pandai ngasih logic buat tick checkbox, sama bawa data produk
     yang dipilih user "tambah ke kerangjang" masuk ke dalam cart;_; cardview utk cart udah gua bikin (item_cart.xml),
     tapi ngga ngerti bikin adapternya. trus btnCart di home, udah gua ganti actionnya ke Cart
     */

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCheckedAll.setOnClickListener {
            binding.tvTotalPrice.text = "10000"
        }

        lifecycleScope.launch{
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

//        binding.btnCheckedAll.drawable = resources.getDrawable(R.drawable.)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}