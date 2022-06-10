package com.example.capstonec22_ps353.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.capstonec22_ps353.databinding.ItemProductBinding
import com.example.capstonec22_ps353.model.ListProductItem
import com.example.capstonec22_ps353.utils.ProductDiffCallback
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class ListProductAdapter : RecyclerView.Adapter<ListProductAdapter.ListViewHolder>() {
    private val listProduct = ArrayList<ListProductItem>()

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun setListProduct(items: List<ListProductItem>){
        val diffProductDiffCallback = ProductDiffCallback(this.listProduct, items)
        val diffProductResult = DiffUtil.calculateDiff(diffProductDiffCallback)
        listProduct.clear()
        listProduct.addAll(items)
        diffProductResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListProductAdapter.ListViewHolder, position: Int) = holder.bind(listProduct[position])

    override fun getItemCount(): Int = listProduct.size

    inner class ListViewHolder(private var binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ListProductItem) {
            binding.root.setOnClickListener {
                onItemClickCallback.onItemClicked(item)
            }

            binding.apply {
                Glide.with(itemView)
                    .load(item.imageUrl)
                    .into(imgProduct)
                tvTitleProduct.text = item.name
                val formatter = NumberFormat.getCurrencyInstance(Locale("in"))
                val currency = formatter.format(item.price)
                tvPriceProduct.text = currency
                tvLoctProduct.text = item.location

            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(item: ListProductItem)
    }

}