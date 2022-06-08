package com.example.capstonec22_ps353.ui.price

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.capstonec22_ps353.R
import com.example.capstonec22_ps353.databinding.FragmentDetailPriceBinding
import com.example.capstonec22_ps353.model.PriceDate
import com.example.capstonec22_ps353.model.PriceList
import com.example.capstonec22_ps353.ui.adapter.ListPriceDateAdapter
import com.example.capstonec22_ps353.utils.SharedViewModel
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import kotlin.math.roundToInt


class DetailPriceFragment : Fragment() {

    private val sharedViewModel: SharedViewModel by activityViewModels()


    private var _binding: FragmentDetailPriceBinding? = null
    private val binding get() = _binding!!

    private lateinit var lineChart: LineChart
    private lateinit var rvPriceDate: RecyclerView

    private var priceList = ArrayList<PriceList>()

    private var position = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailPriceBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvPriceDate = binding.rvPriceDate
        showRecyclerList()

        lineChart = binding.lineChart

        setupViewmodel()

        initLineChart()
        setDataLineChart()

    }

    private fun showRecyclerList() {
        rvPriceDate.layoutManager = LinearLayoutManager(activity)
        rvPriceDate.setHasFixedSize(true)
        val listPriceDateAdapter = ListPriceDateAdapter()
        listPriceDateAdapter.setListPriceDate(listPriceDate)
        rvPriceDate.adapter = listPriceDateAdapter
    }

    private val listPriceDate: ArrayList<PriceDate>
        get() {
            val dataTitle = resources.getStringArray(R.array.dummy_day)
            val dataPrice = resources.getIntArray(R.array.dummy_price)
            val dataDate = resources.getStringArray(R.array.dummy_date)
            val imgDown = ResourcesCompat.getDrawable(resources, R.drawable.price_down, null)
            val imgUp = ResourcesCompat.getDrawable(resources, R.drawable.price_up, null)
            val imgStable = ResourcesCompat.getDrawable(resources, R.drawable.price_stable, null)

            val listPriceDate = ArrayList<PriceDate>()
            for (i in 6 downTo 0) {
                if (i != 0) {
                    if (dataPrice[i] > dataPrice[i - 1]) {
                        val selisih: Float = dataPrice[i] - dataPrice[i - 1].toFloat()
                        val presentase: Int = ((selisih / dataPrice[i]) * 100).roundToInt()
                        val angka = "%.0f".format(selisih)
                        val priceDate = PriceDate(
                            dataTitle[i],
                            dataDate[i],
                            "Rp ${dataPrice[i]}",
                            "Rp $angka ($presentase%)",
                            imgUp,
                            i
                        )
                        listPriceDate.add(priceDate)
                    } else if (dataPrice[i] < dataPrice[i - 1]) {
                        val selisih: Float = dataPrice[i - 1] - dataPrice[i].toFloat()
                        val presentase: Int = ((selisih / dataPrice[i - 1]) * 100).roundToInt()
                        val angka = "%.0f".format(selisih)
                        val priceDate = PriceDate(
                            dataTitle[i],
                            dataDate[i],
                            "Rp ${dataPrice[i]}",
                            "Rp $angka ($presentase%)",
                            imgDown,
                            i
                        )
                        listPriceDate.add(priceDate)
                    }
                } else if (i == 0) {
                    val priceDate = PriceDate(
                        dataTitle[i],
                        dataDate[i],
                        "Rp ${dataPrice[i]}",
                        "Harga Awal",
                        imgStable,
                        i
                    )
                    listPriceDate.add(priceDate)
                } else {
                    val priceDate = PriceDate(
                        dataTitle[i],
                        dataDate[i],
                        "Rp ${dataPrice[i]}",
                        "Harga Stabil",
                        imgStable,
                        i
                    )
                    listPriceDate.add(priceDate)
                }
            }
            return listPriceDate
        }

    private fun setupViewmodel() {
        sharedViewModel.title.observe(viewLifecycleOwner) {
            Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
            binding.tvtesprice.text = it
        }
    }

    private fun setDataLineChart() {
        val entries: ArrayList<Entry> = ArrayList()

        priceList = getPriceList()

        for (i in priceList.indices) {
            val price = priceList[i]
            entries.add(Entry(i.toFloat(), price.PriceY.toFloat()))
        }

        val lineDataSet = LineDataSet(entries, "")

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

    private fun initLineChart() {
        lineChart.apply {
            xAxis.apply {
                setDrawGridLines(false)
                valueFormatter = AxisFormatter()
                position = XAxis.XAxisPosition.BOTTOM
                textSize = 12f
            }
            isAutoScaleMinMaxEnabled = true

            axisRight.isEnabled = false
            axisLeft.setDrawAxisLine(false)
            axisLeft.gridLineWidth = 0.9f
            axisLeft.setDrawGridLinesBehindData(true)
            axisLeft.granularity = 6f
            axisLeft.enableGridDashedLine(20f, 20f, 5f)
            description = null

        }


        lineChart.legend.isEnabled = false
        lineChart.axisRight.isEnabled = false

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
        setupViewmodel()
    }

    private fun setDropdown() {
//        val category = resources.getStringArray(R.array.data_category)
//        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.item_dropdown, category)
//        binding.autoCompleteTV.setAdapter(arrayAdapter)
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

        fun newInstance(position: Int): DetailPriceFragment =
            DetailPriceFragment().apply {
                this.position = position
            }
    }

}