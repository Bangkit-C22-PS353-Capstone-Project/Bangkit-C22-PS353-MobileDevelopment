package com.example.capstonec22_ps353.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.capstonec22_ps353.model.PriceDate

class PriceDateDiffCallBack(private val mOldNoteList: List<PriceDate>, private val mNewNoteList: List<PriceDate>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return mOldNoteList.size
    }

    override fun getNewListSize(): Int {
        return mNewNoteList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldNoteList[oldItemPosition].Title == mNewNoteList[newItemPosition].Title
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldEmployee = mOldNoteList[oldItemPosition]
        val newEmployee = mNewNoteList[newItemPosition]
        return oldEmployee.Title == newEmployee.Title && oldEmployee.Price == newEmployee.Price
    }

}