package com.example.capstonec22_ps353.ui.price

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import com.example.capstonec22_ps353.R
import com.example.capstonec22_ps353.databinding.FragmentPriceBinding
import com.example.capstonec22_ps353.model.PriceList
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter

class PriceFragment : Fragment() {

    private lateinit var lineChart: LineChart
//    private lateinit var lineList: ArrayList<Entry>
//    private lateinit var lineData: LineData
    private var priceList = ArrayList<PriceList>()

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setDropdown()

        lineChart = binding.lineChart

        initLineChart()
        setDataLineChart()

//        lineList = ArrayList()

//        labelChart.add()

//        labelChart.add("w", 1)
//        xValue.add("Sel")
//        xValue.add("Rab")
//        xValue.add("Kam")
//        xValue.add("Jum")
//        xValue.add("Sab")
//        xValue.add("Min")

//        lineList.add(Entry(1f, 100f))
//        lineList.add(Entry(2f, 300f))
//        lineList.add(Entry(3f, 200f))
//        lineList.add(Entry(4f, 600f))
//        lineList.add(Entry(5f, 500f))
//        lineList.add(Entry(6f, 500f))
//        lineList.add(Entry(7f, 500f))





    }

    private fun setDataLineChart() {
        val entries: ArrayList<Entry> = ArrayList()

        priceList = getScoreList()

        for (i in priceList.indices) {
            val price = priceList[i]
            entries.add(Entry(i.toFloat(), price.PriceY.toFloat()))
        }

        val lineDataSet = LineDataSet(entries,"")

        val data = LineData(lineDataSet)
        lineChart.data = data
        lineChart.invalidate()

//        lineDataSet = LineDataSet(lineList, null)
//        lineData = LineData(lineDataSet)
//        lineChart.data = lineData



//        lineChart.setBackgroundColor(Color.TRANSPARENT)

        lineDataSet.apply {
            color = activity?.let { ContextCompat.getColor(it, R.color.label_primer) }!!
//            valueTextColor = activity?.let { ContextCompat.getColor(it, R.color.label_primer) }!!
            lineWidth = 2f
            mode = LineDataSet.Mode.HORIZONTAL_BEZIER
            setDrawValues(false)
//            setDrawHighlightIndicators(false)
            setDrawCircles(false)

            setDrawHorizontalHighlightIndicator(true)

        }

//        lineDataSet.color = activity?.let { ContextCompat.getColor(it, R.color.label_primer) }!!
//        lineDataSet.valueTextColor = activity?.let { ContextCompat.getColor(it, R.color.label_primer) }!!
//        lineDataSet.valueTextSize = 15f
//        lineDataSet.lineWidth = 2f
//        lineDataSet.mode = LineDataSet.Mode.HORIZONTAL_BEZIER
//        lineDataSet.yMax
//        lineDataSet.setDrawValues(false)
//        lineDataSet.setDrawHighlightIndicators(false)
//        lineDataSet.setDrawCircles(false)

    }

    private fun initLineChart() {
        lineChart.apply {
            xAxis.apply {
                setDrawGridLines(false)
                valueFormatter = AxisFormatter()
                position = XAxis.XAxisPosition.BOTTOM
                textSize = 12f
//                textColor = activity?.let { ContextCompat.getColor(it, R.color.label_primer) }!!
            }
            isAutoScaleMinMaxEnabled = true

            axisRight.isEnabled = false
//            axisLeft.textColor = activity?.let { ContextCompat.getColor(it, R.color.label_primer) }!!
            axisLeft.setDrawAxisLine(false)
            axisLeft.gridLineWidth = 0.9f
//            axisLeft.gridColor =
            axisLeft.setDrawGridLinesBehindData(true)
            axisLeft.granularity = 6f
//            axisLeft.mAxisMaximum = 15000f
//            axisLeft.mAxisMinimum = 10000f
            axisLeft.enableGridDashedLine(20f, 20f, 5f)
            description = null

        }


//        lineChart.xAxis.setDrawGridLines(false)
//        lineChart.xAxis.position = XAxis.XAxisPosition.BOTTOM
//        lineChart.xAxis.valueFormatter
        lineChart.legend.isEnabled = false
        lineChart.axisRight.isEnabled = false

//        lineChart.axisLeft.maxWidth = 100f
//        lineChart.axisLeft.setDrawAxisLine(false)
//        lineChart.axisLeft.setDrawLabels(false)
        lineChart.description = null
    }

    inner class AxisFormatter : IndexAxisValueFormatter() {
        override fun getAxisLabel(value: Float, axis: AxisBase?): String {
            val index = value.toInt()
            return if (index < priceList.size) {
                priceList[index].labelX
            } else {
                ""
            }
        }

    }

    override fun onResume() {
        super.onResume()
        setDropdown()
    }

    private fun setDropdown() {
        val category = resources.getStringArray(R.array.data_category)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.item_dropdown, category)
        binding.autoCompleteTV.setAdapter(arrayAdapter)
    }

    private fun getScoreList(): ArrayList<PriceList> {
        priceList.add(PriceList("Sen", 10000))
        priceList.add(PriceList("Sel", 10500))
        priceList.add(PriceList("Rab", 10200))
        priceList.add(PriceList("Kam", 14000))
        priceList.add(PriceList("Jum", 11400))
        priceList.add(PriceList("Sab", 11100))
        priceList.add(PriceList("Min", 12000))

        return priceList
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}