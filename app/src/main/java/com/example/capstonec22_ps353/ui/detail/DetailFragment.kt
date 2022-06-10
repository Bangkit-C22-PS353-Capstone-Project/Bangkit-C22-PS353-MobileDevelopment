package com.example.capstonec22_ps353.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.capstonec22_ps353.R
import com.example.capstonec22_ps353.databinding.FragmentDetailBinding
import com.example.capstonec22_ps353.model.ListProductItem
import com.example.capstonec22_ps353.ui.MainFragmentDirections
import com.example.capstonec22_ps353.utils.SharedViewModel
import kotlinx.coroutines.launch
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*


class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val detailViewModel: DetailViewModel by activityViewModels()

    private val sharedViewModel: SharedViewModel by activityViewModels()

//    private lateinit var navController: NavController


    private val args: DetailFragmentArgs by navArgs()

    private lateinit var product: ListProductItem

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        product = args.listProduct


        activity?.let {
            Glide.with(it)
                .load(product.imageUrl)
                .into(binding.imgDetail)
        }


        setupActionButton()

        if (product.categoryId > 3) {
            binding.tvMinimum.visibility = View.GONE
        }

        val df = DecimalFormat("#,###")

//        val ribuan = product.price/1000
//        val puluhan = (product.price%1000)

        binding.tvTittle.text = product.name
        binding.tvPrice.text = "Rp ${df.format(product.price)}"
        binding.tvStok.text = "${product.stock}"
        binding.tvDeskripsi.text = product.description
        binding.tvLokasi.text = "Dikirim dari ${product.location}"

    }

    private fun setupActionButton() {
        lifecycleScope.launch {
            sharedViewModel.navController.observe(viewLifecycleOwner) {
                val navController = it

                binding.apply {
                    btnBack.setOnClickListener {
                        navController.popBackStack()
                    }

                    btnAddToCart.setOnClickListener {
                        val product = args.listProduct
                        val action =
                            DetailFragmentDirections.actionDetailFragmentToBottomSheetDetailFragment(
                                product
                            )
                        navController.navigate(action)
                    }

                }


                val callback = object : OnBackPressedCallback(true) {
                    override fun handleOnBackPressed() {
                        navController.popBackStack()
                    }
                }
                requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)


            }

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}