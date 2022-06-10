package com.example.capstonec22_ps353.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.capstonec22_ps353.databinding.ItemHistoryBinding
import com.example.capstonec22_ps353.model.Product
import com.example.capstonec22_ps353.utils.ProductDiffCallback

//class ListHistoryAdapter : RecyclerView.Adapter<ListHistoryAdapter.ListViewHolder>() {

//    private val listProduct = ArrayList<Product>()
//
//    fun setListHistory(items: List<Product>){
//        val diffProductDiffCallback = ProductDiffCallback(this.listProduct, items)
//        val diffProductResult = DiffUtil.calculateDiff(diffProductDiffCallback)
//        listProduct.clear()
//        listProduct.addAll(items)
//        diffProductResult.dispatchUpdatesTo(this)
//    }

//    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
//        val binding = ItemHistoryBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
//    return ListViewHolder(binding)
//    }
//
//    override fun onBindViewHolder(holder: ListHistoryAdapter.ListViewHolder, position: Int) = holder.bind(listProduct[position])
//
//    override fun getItemCount(): Int {
//        TODO("Not yet implemented")
//    }
//
//    inner class ListViewHolder(private var binding: ItemHistoryBinding) : RecyclerView.ViewHolder(binding.root) {
//        fun bind(item: Product) {
//            binding.apply {
//                Glide.with(itemView)
//                    .load(item.Image)
//                    .into(imgProduct)
//                tvTitle.text = item.Title
//                tvPrice.text = item.Price
////                tvQuantity.text = item.Quantity
//
//                btnBuyAgain.setOnClickListener {
////
//                }
//            }
//        }
//    }

//}