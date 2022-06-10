package com.example.capstonec22_ps353.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.capstonec22_ps353.databinding.ItemCartBinding
import com.example.capstonec22_ps353.model.Cart
import com.example.capstonec22_ps353.model.ListCartItem
import com.example.capstonec22_ps353.utils.CartDiffCallBack
import java.text.DecimalFormat
import kotlin.collections.ArrayList

class ListCartAdapter : RecyclerView.Adapter<ListCartAdapter.ListViewHolder>() {

    private val listCart = ArrayList<ListCartItem>()
//    private var isEnable = false
//    private val priceSelectedList = mutableListOf<Int>()
//    private lateinit var cart : MutableList<Cart>

    private val checked = true

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun setListCart(items: List<ListCartItem>){
        val diffCartDiffCallback = CartDiffCallBack(this.listCart, items)
        val diffCartResult = DiffUtil.calculateDiff(diffCartDiffCallback)
        listCart.clear()
        listCart.addAll(items)
        diffCartResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val binding = ItemCartBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListCartAdapter.ListViewHolder, position: Int) = holder.bind(listCart[position], position)

    override fun getItemCount(): Int = listCart.size

    inner class ListViewHolder(private var binding: ItemCartBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ListCartItem, position: Int) {
            val df = DecimalFormat("#,###")

//            val itemCart = cart[position]

            var qty = item.qty
//            if (item.categoryId < 4) {
//                qty = 5
//            }

//            binding.checkbox.setOnCheckedChangeListener { _, checked ->
//                binding.checkbox.setOnLongClickListener {
//
//
//                    true
//                }
//
////                binding.checkbox.isChecked = checked
//            }

            binding.edQty.setText("$qty kg")

            var price = item.price*qty

            binding.tvCartPrice.text = "Rp ${df.format(price)}"

            binding.btnPlus.setOnClickListener {
                if (qty<item.stock){
                    qty += if (item.categoryId < 4) {

                        5
                    } else {
                        1
                    }
                }

                binding.edQty.setText("$qty kg")

                price = item.price*qty

                binding.tvCartPrice.text = "Rp ${df.format(price)}"
            }

            binding.btnMinus.setOnClickListener {
                if (item.categoryId < 4) {
                    if (qty > 5) {
                        qty -= 5
                    }
                } else {
                    if (qty > 1) {
                        qty -= 1
                    }
                }

                binding.edQty.setText("$qty kg")

                price = item.price*qty

                binding.tvCartPrice.text = "Rp ${df.format(price)}"

            }

            binding.apply {
                Glide.with(itemView)
                    .load(item.imageUrl)
                    .into(imgProduct)
                tvCartTitle.text = item.name
//                val formatter = NumberFormat.getCurrencyInstance(Locale("in"))
//                val currency = formatter.format(item.price)
//                tvCartPrice.text = item.price.toString()
                tvCartStock.text = "Stok : ${item.stock}"

            }

            binding.checkbox.setOnCheckedChangeListener { _, checked ->
                onItemClickCallback.onItemClicked(item, checked, price)
            }

//            binding.checkbox.setOnLongClickListener {
//                selectItem(binding, itemCart, price)
//                true
//            }
//
//            binding.checkbox.setOnCheckedChangeListener { _, checked ->
//                if (priceSelectedList.contains(price)){
//                    itemCart.selected = false
//                    if (priceSelectedList.isEmpty()){
//                        isEnable = false
//                    }
//                } else if (isEnable) {
//                    selectItem(binding, itemCart, price)
//                }
//            }

        }
    }

//    private fun selectItem(binding: ItemCartBinding, itemCart: Cart, price: Int) {
//        isEnable = true
//        priceSelectedList.add(price)
//        itemCart.selected = true
//    }


    interface OnItemClickCallback {
        fun onItemClicked(item: ListCartItem, checked: Boolean, price: Int)
    }
}