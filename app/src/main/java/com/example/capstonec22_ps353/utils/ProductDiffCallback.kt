package com.example.capstonec22_ps353.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.capstonec22_ps353.model.ListProductItem
import com.example.capstonec22_ps353.model.Product

class ProductDiffCallback(private val mOldNoteList: List<ListProductItem>, private val mNewNoteList: List<ListProductItem>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return mOldNoteList.size
    }

    override fun getNewListSize(): Int {
        return mNewNoteList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldNoteList[oldItemPosition].name == mNewNoteList[newItemPosition].name
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldEmployee = mOldNoteList[oldItemPosition]
        val newEmployee = mNewNoteList[newItemPosition]
        return oldEmployee.name == newEmployee.name && oldEmployee.price == newEmployee.price
    }

}