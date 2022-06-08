package com.example.capstonec22_ps353.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.capstonec22_ps353.databinding.ItemPriceListBinding
import com.example.capstonec22_ps353.model.PriceDate
import com.example.capstonec22_ps353.utils.PriceDateDiffCallBack

class ListPriceDateAdapter : RecyclerView.Adapter<ListPriceDateAdapter.ListViewHolder>()  {
    private val listPriceDate = ArrayList<PriceDate>()

    fun setListPriceDate(items: List<PriceDate>){
        val diffPriceDateDiffCallBack = PriceDateDiffCallBack(this.listPriceDate, items)
        val diffPriceDateResult = DiffUtil.calculateDiff(diffPriceDateDiffCallBack)
        listPriceDate.clear()
        listPriceDate.addAll(items)
        diffPriceDateResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val binding = ItemPriceListBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListPriceDateAdapter.ListViewHolder, position: Int) = holder.bind(listPriceDate[position])

    override fun getItemCount(): Int = listPriceDate.size

    inner class ListViewHolder(private var binding: ItemPriceListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PriceDate) {
            binding.apply {
                Glide.with(itemView)
                    .load(item.Indicator)
                    .into(imgPresentase)
                tvTittle.text = item.Title
                tvPrice.text = item.Price
                tvDate.text = item.Date
                tvPresentase.text = item.Presentase
            }
        }
    }

}