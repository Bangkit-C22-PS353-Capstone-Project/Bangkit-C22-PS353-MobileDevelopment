package com.example.capstonec22_ps353.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.capstonec22_ps353.databinding.ItemProductBinding
import com.example.capstonec22_ps353.model.Product
import com.example.capstonec22_ps353.utils.ProductDiffCallback

class ListProductAdapter : RecyclerView.Adapter<ListProductAdapter.ListViewHolder>() {
    private val listProduct = ArrayList<Product>()

    fun setListProduct(items: List<Product>){
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
        fun bind(item: Product) {
            binding.apply {
                Glide.with(itemView)
                    .load(item.Image)
                    .into(imgProduct)
                tvTitleProduct.text = item.Title
                tvPriceProduct.text = item.Price
                tvLoctProduct.text = item.Location

            }
        }
    }



}