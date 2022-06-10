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
import com.example.capstonec22_ps353.ui.adapter.ListCartAdapter
import com.example.capstonec22_ps353.utils.SharedViewModel
import kotlinx.coroutines.launch

class CartFragment : Fragment() {

    private lateinit var rvCart: RecyclerView
    private lateinit var listCartAdapter: ListCartAdapter

    private val cartViewModel: CartViewModel by activityViewModels()

    private val sharedViewModel: SharedViewModel by activityViewModels()

    private var _binding: FragmentCartBinding?=null
    private val binding get() = _binding!!

    private var total = 0

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

        rvCart = binding.rvCart
        binding
        listCartAdapter = ListCartAdapter()


        listCartAdapter.setOnItemClickCallback(object : ListCartAdapter.OnItemClickCallback {
            override fun onItemClicked(item: ListCartItem, checked: Boolean, price: Int) {
                if (checked){
                    total += price
                    binding.tvTotalPrice.text = total.toString()
                } else {
                    total -= price
                    binding.tvTotalPrice.text = total.toString()
                }
            }

        })

        cartViewModel.getAllCart()
//            .observe(viewLifecycleOwner) { listCart ->
//            listCartAdapter.setListCart(listCart)
//        }

//        cartViewModel.getCartByUser(1)

        cartViewModel.listCart.observe(viewLifecycleOwner) {
            listCartAdapter.setListCart(it)
        }

        showRecyclerList()

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

    private fun totalPrice(price: Int, checked: Boolean) {


    }

    private fun showRecyclerList() {
        rvCart.layoutManager = LinearLayoutManager(activity)
        rvCart.setHasFixedSize(true)
        rvCart.adapter = listCartAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}