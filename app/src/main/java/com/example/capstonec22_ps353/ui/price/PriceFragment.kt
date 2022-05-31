package com.example.capstonec22_ps353.ui.price

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.capstonec22_ps353.R
import com.example.capstonec22_ps353.databinding.FragmentLoginBinding
import com.example.capstonec22_ps353.databinding.FragmentPriceBinding
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.utils.ColorTemplate


class PriceFragment : Fragment() {

    private lateinit var lineDataSet: LineDataSet
    private lateinit var lineList: ArrayList<Entry>
    private lateinit var lineData: LineData

    private var _binding: FragmentPriceBinding?=null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPriceBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lineList = ArrayList()
        lineList.add(Entry(10f, 100f))
        lineList.add(Entry(20f, 300f))
        lineList.add(Entry(30f, 200f))
        lineList.add(Entry(40f, 600f))
        lineList.add(Entry(50f, 500f))

        lineDataSet = LineDataSet(lineList, "Count")
        lineData = LineData(lineDataSet)
        binding.lineChart.data = lineData
        lineDataSet.setColors(ColorTemplate.JOYFUL_COLORS)
        lineDataSet.valueTextColor = Color.BLUE
        lineDataSet.valueTextSize = 15f



    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}

private fun LineDataSet.setColors(joyfulColors: IntArray?) {

}
