package com.example.capstonec22_ps353.ui.cart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.capstonec22_ps353.databinding.FragmentCartBinding
import com.example.capstonec22_ps353.model.ListCartItem
import com.example.capstonec22_ps353.preferences.PrefViewModel
import com.example.capstonec22_ps353.ui.adapter.ListCartAdapter
import com.example.capstonec22_ps353.utils.SharedViewModel
import kotlinx.coroutines.launch
import java.text.DecimalFormat

class CartFragment : Fragment() {

    private lateinit var rvCart: RecyclerView
    private lateinit var listCartAdapter: ListCartAdapter

    private val cartViewModel: CartViewModel by activityViewModels()
    private val prefViewModel: PrefViewModel by activityViewModels()
    private val sharedViewModel: SharedViewModel by activityViewModels()


    private var _binding: FragmentCartBinding?=null
    private val binding get() = _binding!!

//    private var total = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return (binding.root)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvCart = binding.rvCart
        binding
        listCartAdapter = ListCartAdapter()
        val df = DecimalFormat("#,###")

        prefViewModel.getInfo().observe(viewLifecycleOwner) { user ->
            cartViewModel.getCartByUserId(user.userId.toInt())
        }

//            .observe(viewLifecycleOwner) { listCart ->
//            listCartAdapter.setListCart(listCart)
//        }

//        cartViewModel.getCartByUser(1)

        cartViewModel.listCart.observe(viewLifecycleOwner) {
            listCartAdapter.setListCart(it)
        }

        showRecyclerList()

//        binding.btnCheckedAll.setOnClickListener {
//            binding.tvTotalPrice.text = "10000"
//        }

        lifecycleScope.launch{
            sharedViewModel.navController.observe(viewLifecycleOwner) {
                val navController = it
                val callback = object : OnBackPressedCallback(true) {
                    override fun handleOnBackPressed() {
                        navController.popBackStack()
                    }
                }

                listCartAdapter.setOnItemClickCallback(object : ListCartAdapter.OnItemClickCallback {
                    override fun onItemClicked(item: ListCartItem, price: Int, qty: Int) {
                        val action = CartFragmentDirections.actionCartFragmentToCheckoutFragment(item, qty)
                        navController.navigate(action)
                    }

                })

                requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
                binding.btnBack.setOnClickListener {
                    navController.popBackStack()
                }
            }
        }

//        binding.btnCheckedAll.drawable = resources.getDrawable(R.drawable.)

    }

    private fun showRecyclerList() {
        rvCart.layoutManager = LinearLayoutManager(activity)
        rvCart.setHasFixedSize(true)
        rvCart.adapter = listCartAdapter
    }

    override fun onResume() {
        super.onResume()
        showRecyclerList()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}