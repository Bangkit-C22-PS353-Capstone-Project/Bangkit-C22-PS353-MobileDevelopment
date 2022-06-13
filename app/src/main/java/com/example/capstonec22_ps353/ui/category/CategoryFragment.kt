package com.example.capstonec22_ps353.ui.category

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.capstonec22_ps353.R
import com.example.capstonec22_ps353.databinding.FragmentCategoryBinding
import com.example.capstonec22_ps353.model.ListProductItem
import com.example.capstonec22_ps353.ui.MainFragmentDirections
import com.example.capstonec22_ps353.ui.adapter.ListProductAdapter
import com.example.capstonec22_ps353.ui.adapter.SectionPagerAdapter
import com.example.capstonec22_ps353.utils.SharedViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.launch

class CategoryFragment : Fragment() {

    private val sharedViewModel: SharedViewModel by activityViewModels()
    private val categoryViewModel: CategoryViewModel by activityViewModels()

    private val args: CategoryFragmentArgs by navArgs()

    private var _binding: FragmentCategoryBinding? = null
    private val binding get() = _binding!!
    private lateinit var listProductAdapter: ListProductAdapter
    private lateinit var rvProduct: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoryBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val category = args.category

        rvProduct = binding.rvProduct
        listProductAdapter = ListProductAdapter()

        categoryViewModel.listProduct2.observe(viewLifecycleOwner) {
            listProductAdapter.setListProduct(it)
        }

        listProductAdapter.setOnItemClickCallback(object :
            ListProductAdapter.OnItemClickCallback {
            override fun onItemClicked(item: ListProductItem) {
//                val action = MainFragmentDirections.actionMainFragmentToDetailFragment(item)
//                navController.navigate(action)
            }
        })

        binding.tvCategory.text = category
        showRecyclerList()
//        val args = CategoryFragmentArgs.fromBundle(category)
        setupTabLayout()

            sharedViewModel.navController.observe(viewLifecycleOwner) {
                val navController = it
                val callback = object : OnBackPressedCallback(true) {
                    override fun handleOnBackPressed() {
                        navController.popBackStack()
                    }
                }


//                 navController.previousBackStackEntry

                requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
                binding.tvCategory.setOnClickListener {
//                    navController.navigate(R.id.action_categoryFragment_to_loginFragment)
//                    navController.navigate(R.id.action_loginFragment_to_mainFragment)
                }

                //navigation on clicked Cart button to Cart Fragment
//                requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,callback)
                binding.btnCart.setOnClickListener {
                    navController.navigate(R.id.action_categoryFragment_to_cartFragment)
                }

                requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
                binding.btnBack.setOnClickListener {
                    navController.popBackStack()
                }
            }


    }

    private fun showRecyclerList() {
        rvProduct.layoutManager = GridLayoutManager(activity, 2)
        rvProduct.setHasFixedSize(true)
//        listProductAdapter.setListProduct(listProduct)
        rvProduct.adapter = listProductAdapter
    }

    private fun setupTabLayout() {
        binding.apply {
            val fragmentBeras = mutableListOf<Fragment>(
                DetailCategoryFragment.newInstance(DetailCategoryFragment.BERAS3),
                DetailCategoryFragment.newInstance(DetailCategoryFragment.BERAS2),
                DetailCategoryFragment.newInstance(DetailCategoryFragment.BERAS1)
            )

            val fragmentBawangM = mutableListOf<Fragment>(
                DetailCategoryFragment.newInstance(DetailCategoryFragment.BAWANGMERAH)
            )

            val fragmentBawangP = mutableListOf<Fragment>(
                DetailCategoryFragment.newInstance(DetailCategoryFragment.BAWANGPUTIH)
            )

            val fragmentCabaiM = mutableListOf<Fragment>(
                DetailCategoryFragment.newInstance(DetailCategoryFragment.CABAIM1),
                DetailCategoryFragment.newInstance(DetailCategoryFragment.CABAIM2)
            )

            val fragmentCabaiR = mutableListOf<Fragment>(
                DetailCategoryFragment.newInstance(DetailCategoryFragment.CABAIR1),
                DetailCategoryFragment.newInstance(DetailCategoryFragment.CABAIR2)
            )

            val fragmentTitleBeras = mutableListOf(
                getString(R.string.beras3),
                getString(R.string.beras2),
                getString(R.string.beras1),
            )

            val fragmentTitleBawangM = mutableListOf(
                getString(R.string.bawangM)
            )

            val fragmentTitleBawangP = mutableListOf(
                getString(R.string.bawangP)
            )

            val fragmentTitleCabaiM = mutableListOf(
                getString(R.string.cabaiM1),
                getString(R.string.cabaiM2)
            )

            val fragmentTitleCabaiR = mutableListOf(
                getString(R.string.cabaiR1),
                getString(R.string.cabaiR2)
            )

            when (tvCategory.text.toString()) {
                "Beras" -> {
                    viewPagerCategory.adapter =
                        SectionPagerAdapter(requireActivity(), fragmentBeras)
                    TabLayoutMediator(tbCategory, viewPagerCategory) { tab, position ->
                        tab.text = fragmentTitleBeras[position]
                    }.attach()

                    tbCategory.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                        override fun onTabSelected(tab: TabLayout.Tab?) {
                            when (tab?.position) {
                                DetailCategoryFragment.BERAS1 -> {
                                    sharedViewModel.setTitle(getString(R.string.beras1))
                                }

                                DetailCategoryFragment.BERAS2 -> {
                                    sharedViewModel.setTitle(getString(R.string.beras2))
                                }

                                DetailCategoryFragment.BERAS3 -> {
                                    sharedViewModel.setTitle(getString(R.string.beras3))
                                }
                            }
                        }

                        override fun onTabUnselected(tab: TabLayout.Tab?) {}
                        override fun onTabReselected(tab: TabLayout.Tab?) {}
                    })
                    sharedViewModel.setTitle(getString(R.string.beras1))
                }

                "Bawang Merah" -> {
                    viewPagerCategory.adapter =
                        SectionPagerAdapter(requireActivity(), fragmentBawangM)
                    TabLayoutMediator(tbCategory, viewPagerCategory) { tab, position ->
                        tab.text = fragmentTitleBawangM[position]
                    }.attach()

                    sharedViewModel.setTitle(getString(R.string.bawangM))
                }

                "Bawang Putih" -> {
                    viewPagerCategory.adapter =
                        SectionPagerAdapter(requireActivity(), fragmentBawangP)
                    TabLayoutMediator(tbCategory, viewPagerCategory) { tab, position ->
                        tab.text = fragmentTitleBawangP[position]
                    }.attach()

                    sharedViewModel.setTitle(getString(R.string.bawangP))
                }

                "Cabai Merah" -> {
                    viewPagerCategory.adapter =
                        SectionPagerAdapter(requireActivity(), fragmentCabaiM)
                    TabLayoutMediator(tbCategory, viewPagerCategory) { tab, position ->
                        tab.text = fragmentTitleCabaiM[position]
                    }.attach()

                    tbCategory.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                        override fun onTabSelected(tab: TabLayout.Tab?) {
                            when (tab?.position) {
                                DetailCategoryFragment.CABAIM1 -> {
                                    sharedViewModel.setTitle(getString(R.string.cabaiM1))
                                }

                                DetailCategoryFragment.CABAIM2 -> {
                                    sharedViewModel.setTitle(getString(R.string.cabaiM2))
                                }
                            }
                        }

                        override fun onTabUnselected(tab: TabLayout.Tab?) {}
                        override fun onTabReselected(tab: TabLayout.Tab?) {}
                    })
                    sharedViewModel.setTitle(getString(R.string.cabaiM1))

                }

                "Cabai Rawit" -> {
                    viewPagerCategory.adapter =
                        SectionPagerAdapter(requireActivity(), fragmentCabaiR)
                    TabLayoutMediator(tbCategory, viewPagerCategory) { tab, position ->
                        tab.text = fragmentTitleCabaiR[position]
                    }.attach()

                    tbCategory.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                        override fun onTabSelected(tab: TabLayout.Tab?) {
                            when (tab?.position) {
                                DetailCategoryFragment.CABAIR1 -> {
                                    sharedViewModel.setTitle(getString(R.string.cabaiR1))
                                }

                                DetailCategoryFragment.CABAIR2 -> {
                                    sharedViewModel.setTitle(getString(R.string.cabaiR2))
                                }
                            }
                        }

                        override fun onTabUnselected(tab: TabLayout.Tab?) {}
                        override fun onTabReselected(tab: TabLayout.Tab?) {}
                    })
                    sharedViewModel.setTitle(getString(R.string.cabaiR1))

                }

            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}