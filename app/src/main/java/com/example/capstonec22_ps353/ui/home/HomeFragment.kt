package com.example.capstonec22_ps353.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.capstonec22_ps353.R
import com.example.capstonec22_ps353.databinding.FragmentHomeBinding
import com.example.capstonec22_ps353.model.Product
import com.example.capstonec22_ps353.ui.MainFragmentDirections
import com.example.capstonec22_ps353.ui.adapter.ListProductAdapter
import com.example.capstonec22_ps353.utils.SharedViewModel
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {
    private lateinit var rvProduct: RecyclerView
//    private val list = ArrayList<Product>()

    private val sharedViewModel: SharedViewModel by activityViewModels()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var listProductAdapter: ListProductAdapter

    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvProduct = binding.rvProduct
        listProductAdapter = ListProductAdapter()

        setupActionButton()

        val imageList = ArrayList<SlideModel>()

        imageList.add(SlideModel(R.drawable.farm1))
        imageList.add(SlideModel(R.drawable.farm2))
        imageList.add(SlideModel(R.drawable.farm3))

        binding.imgSliderHome.setImageList(imageList, ScaleTypes.FIT)

        showRecyclerList()

    }

    private fun showRecyclerList() {
        rvProduct.layoutManager = GridLayoutManager(activity, 2)
        rvProduct.setHasFixedSize(true)
        listProductAdapter.setListProduct(listProduct)
        rvProduct.adapter = listProductAdapter
    }

    private val listProduct: ArrayList<Product>
        get() {
            val dataImage = resources.getStringArray(R.array.data_image_product)
            val dataTitle = resources.getStringArray(R.array.data_title_product)
            val dataPrice = resources.getStringArray(R.array.data_price_product)
            val dataLocation = resources.getStringArray(R.array.data_location_product)
            val listProduct = ArrayList<Product>()
            for (i in dataImage.indices) {
                val product = Product(dataImage[i], dataTitle[i], dataPrice[i], dataLocation[i])
                listProduct.add(product)
            }
            return listProduct
        }

    private fun setupActionButton() {
        lifecycleScope.launch {
            sharedViewModel.navController.observe(viewLifecycleOwner) {
                navController = it

                listProductAdapter.setOnItemClickCallback(object :
                    ListProductAdapter.OnItemClickCallback {
                    override fun onItemClicked(item: Product) {
                        val action = MainFragmentDirections.actionMainFragmentToDetailFragment(item)
                        navController.navigate(action)
                    }

                })

                binding.apply {
                    btnCart.setOnClickListener {
                        navController.navigate(R.id.action_mainFragment_to_cartFragment)
                    }

                    tvBeras.apply {
                        setOnClickListener {
                            val text = text.toString()
                            val action =
                                MainFragmentDirections.actionMainFragmentToCategoryFragment(text)
                            navController.navigate(action)
                        }
                    }

                    tvBawangMerah.apply {
                        setOnClickListener {
                            val text = text.toString()
                            val action =
                                MainFragmentDirections.actionMainFragmentToCategoryFragment(text)
                            navController.navigate(action)
                        }
                    }

                    tvBawangPutih.apply {
                        setOnClickListener {
                            val text = text.toString()
                            val action =
                                MainFragmentDirections.actionMainFragmentToCategoryFragment(text)
                            navController.navigate(action)
                        }
                    }

                    tvCabaiMerah.apply {
                        setOnClickListener {
                            val text = text.toString()
                            val action =
                                MainFragmentDirections.actionMainFragmentToCategoryFragment(text)
                            navController.navigate(action)
                        }
                    }

                    tvCabaiRawit.apply {
                        setOnClickListener {
                            val text = text.toString()
                            val action =
                                MainFragmentDirections.actionMainFragmentToCategoryFragment(text)
                            navController.navigate(action)
                        }
                    }
                }
            }
        }
    }

//    private fun setNav(navController: NavController) {
//
//
//        Toast.makeText(activity, "ADA", Toast.LENGTH_SHORT).show()
//
//    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}