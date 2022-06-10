package com.example.capstonec22_ps353.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.capstonec22_ps353.databinding.FragmentBottomSheetDetailBinding
import com.example.capstonec22_ps353.model.AddCart
import com.example.capstonec22_ps353.model.ListProductItem
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.launch


class BottomSheetDetailFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentBottomSheetDetailBinding? = null
    private val binding get() = _binding!!

    private val detailViewModel: DetailViewModel by activityViewModels()


    private val args: BottomSheetDetailFragmentArgs by navArgs()

    private lateinit var product: ListProductItem

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBottomSheetDetailBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        product = args.listDetailProduct

        var cek = false



        detailViewModel.listCart.observe(viewLifecycleOwner) { listCart ->
            for (i in listCart.indices) {
                if (product.productId == listCart[i].productId) {
                    cek = true
                    break
                }
            }

            if (cek) {
                Toast.makeText(activity, "Cek : $cek", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(activity, "Cek : $cek", Toast.LENGTH_SHORT).show()
            }


        }

        activity?.let {
            Glide.with(it)
                .load(product.imageUrl)
                .into(binding.imgProduct)
        }
        binding.tvPrice.text = "Rp ${product.price}"
        binding.tvStok.text = "Stok: ${product.stock}"

        binding.btnClose.setOnClickListener {
            dismiss()
        }

        setupQuantity()


    }

    override fun onResume() {
        super.onResume()
        setupQuantity()
    }

    private fun setupQuantity() {
        var qty: Int
        qty = 1
        if (product.categoryId < 4) {
            qty = 5
        }

        binding.tvQuantity.setText("$qty kg")


        binding.btnPlus.setOnClickListener {
            qty += if (product.categoryId < 4) {
                5
            } else {
                1
            }

            binding.tvQuantity.setText("$qty kg")
        }

        binding.btnMinus.setOnClickListener {
            if (product.categoryId < 4) {
                if (qty > 5) {
                    qty -= 5
                }
            } else {
                if (qty > 1) {
                    qty -= 1
                }
            }


            binding.tvQuantity.setText("$qty kg")

        }

        binding.btnAddToCart.setOnClickListener {
            val addCart = AddCart(1,product.productId, product.name, product.price, product.stock, product.imageUrl, qty)
            detailViewModel.addToCart(addCart)
            detailViewModel.resAdd.observe(viewLifecycleOwner) {
                Toast.makeText(activity, it.message, Toast.LENGTH_SHORT).show()
            }
            dismiss()
        }

    }

//    private fun cekKeranjang(cek: Boolean) {
//        lifecycleScope.launch {
//            if (cek) {
//                Log.d("CEK", "cekKeranjang: ada")
//            } else {
//                Log.d("CEK", "cekKeranjang: gada")
//            }
//        }
//
//    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}