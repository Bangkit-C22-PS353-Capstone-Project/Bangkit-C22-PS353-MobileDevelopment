package com.example.capstonec22_ps353.ui.price

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.capstonec22_ps353.R
import com.example.capstonec22_ps353.databinding.FragmentPriceBinding
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet

class PriceFragment : Fragment() {

    private lateinit var lineDataSet: LineDataSet
    private lateinit var lineList: ArrayList<Entry>
    private lateinit var lineData: LineData

    private var _binding: FragmentPriceBinding?=null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPriceBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        setDropdown()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setDropdown()



        val lineChart = binding.lineChart

        lineList = ArrayList()
        lineList.add(Entry(1f, 100f))
        lineList.add(Entry(2f, 300f))
        lineList.add(Entry(3f, 200f))
        lineList.add(Entry(4f, 600f))
        lineList.add(Entry(5f, 500f))
        lineList.add(Entry(6f, 500f))
        lineList.add(Entry(7f, 500f))

        lineDataSet = LineDataSet(lineList, null)
        lineData = LineData(lineDataSet)
        lineChart.data = lineData

        lineChart.xAxis.setDrawGridLines(false)
        lineChart.xAxis.setDrawAxisLine(false)

        lineChart.xAxis.position = XAxis.XAxisPosition.BOTTOM
        lineChart.legend.isEnabled = false
        lineChart.axisRight.isEnabled = false


//        lineChart.axisLeft.isEnabled = false
        lineChart.description = null

//        lineChart.setBackgroundColor(Color.TRANSPARENT)
        lineDataSet.setColors(R.color.label_primer)
//        lineDataSet.valueTextColor = ContextCompat.getColor(con, R.color.label_primer)
        lineDataSet.valueTextSize = 15f
        lineDataSet.lineWidth = 3f
        lineDataSet.mode = LineDataSet.Mode.HORIZONTAL_BEZIER
        lineDataSet.setDrawValues(false)
        lineDataSet.setDrawHighlightIndicators(false)
        lineDataSet.setDrawCircles(false)



    }

    private fun setDropdown() {
        val category = resources.getStringArray(R.array.data_category)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.item_dropdown, category)
        binding.autoCompleteTV.setAdapter(arrayAdapter)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}