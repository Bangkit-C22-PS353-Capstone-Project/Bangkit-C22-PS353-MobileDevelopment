package com.example.capstonec22_ps353.ui.category

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import com.example.capstonec22_ps353.R
import com.example.capstonec22_ps353.databinding.FragmentDetailCategoryBinding
import com.example.capstonec22_ps353.model.PriceList
import com.example.capstonec22_ps353.utils.SharedViewModel
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter

class DetailCategoryFragment : Fragment() {

    private lateinit var lineChart: LineChart
    private var priceList = ArrayList<PriceList>()


    private var _binding: FragmentDetailCategoryBinding? = null
    private val binding get() = _binding!!

    private val sharedViewModel: SharedViewModel by activityViewModels()

    private var position = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailCategoryBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedViewModel.title.observe(viewLifecycleOwner) {
            binding.tvDetailCategory.text = it
        }

        lineChart = binding.lineChart
        initLineChart()
        setDataLineChart()

    }

    private fun initLineChart() {
        lineChart.apply {
            xAxis.apply {
                setDrawGridLines(false)
                valueFormatter = AxisFormatter()
                position = XAxis.XAxisPosition.BOTTOM
                textSize = 12f
                setDrawLabels(false)
            }
            isAutoScaleMinMaxEnabled = true

            axisRight.isEnabled = false
            axisLeft.setDrawAxisLine(false)
            axisLeft.gridLineWidth = 0.9f
            axisLeft.setDrawLabels(false)
            axisLeft.setDrawGridLinesBehindData(true)
            axisLeft.granularity = 6f
            axisLeft.enableGridDashedLine(20f, 20f, 5f)
            description = null

        }


        lineChart.legend.isEnabled = false
        lineChart.axisRight.isEnabled = false

        lineChart.description = null
    }

    private fun setDataLineChart() {
        val entries: ArrayList<Entry> = ArrayList()

        priceList = getPriceList()

        for (i in priceList.indices) {
            val price = priceList[i]
            entries.add(Entry(i.toFloat(), price.PriceY.toFloat()))
        }

        val lineDataSet = LineDataSet(entries,"")

        val data = LineData(lineDataSet)
        lineChart.data = data
        lineChart.invalidate()

        lineDataSet.apply {
            color = activity?.let { ContextCompat.getColor(it, R.color.label_primer) }!!
            lineWidth = 2f
            mode = LineDataSet.Mode.HORIZONTAL_BEZIER
            setDrawValues(false)
            setDrawCircles(false)

            setDrawHorizontalHighlightIndicator(true)

        }


    }

    private fun getPriceList(): ArrayList<PriceList> {
        val labelX = resources.getStringArray(R.array.dummy_day_label)
        val labelY = resources.getIntArray(R.array.dummy_price)

        for (i in labelX.indices) {
            val label = PriceList(labelX[i], labelY[i])
            priceList.add(label)
        }

//        priceList.add(PriceList("Sen", 10000))
//        priceList.add(PriceList("Sel", 10500))
//        priceList.add(PriceList("Rab", 10200))
//        priceList.add(PriceList("Kam", 14000))
//        priceList.add(PriceList("Jum", 11400))
//        priceList.add(PriceList("Sab", 11100))
//        priceList.add(PriceList("Min", 12000))

        return priceList
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

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val BERAS1 = 0
        const val BERAS2 = 1
        const val BERAS3 = 2

        const val BAWANGMERAH = 0

        const val BAWANGPUTIH = 0

        const val CABAIM1 = 0
        const val CABAIM2 = 1

        const val CABAIR1 = 0
        const val CABAIR2 = 1

        fun newInstance(position: Int): DetailCategoryFragment =
            DetailCategoryFragment().apply {
                this.position = position
            }
    }

}