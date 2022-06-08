package com.example.capstonec22_ps353.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.capstonec22_ps353.databinding.FragmentDetailBinding
import com.example.capstonec22_ps353.utils.SharedViewModel
import kotlinx.coroutines.launch


class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding?=null
    private val binding get() = _binding!!

    private val sharedViewModel: SharedViewModel by activityViewModels()

//    private lateinit var navController: NavController

    private val args: DetailFragmentArgs by navArgs()

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

        val product = args.product

        activity?.let {
            Glide.with(it)
                .load(product.Image)
                .into(binding.imgDetail)
        }

        setupActionButton()

        binding.tvTittle.text = product.Title
        binding.tvPrice.text = product.Price
        binding.tvLokasi.text = "Dikirim dari ${product.Location}"

    }

    private fun setupActionButton() {
        lifecycleScope.launch {
            sharedViewModel.navController.observe(viewLifecycleOwner) {
                val navController = it

                binding.apply {
                    btnBack.setOnClickListener {
                        navController.popBackStack()
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