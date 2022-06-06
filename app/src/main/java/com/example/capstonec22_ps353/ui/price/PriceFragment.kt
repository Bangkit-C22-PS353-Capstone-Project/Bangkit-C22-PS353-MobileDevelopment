package com.example.capstonec22_ps353.ui.price

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.activityViewModels
import com.example.capstonec22_ps353.R
import com.example.capstonec22_ps353.databinding.FragmentPriceBinding
import com.example.capstonec22_ps353.model.PriceList
import com.example.capstonec22_ps353.ui.adapter.SectionPagerAdapter
import com.example.capstonec22_ps353.ui.category.DetailCategoryFragment
import com.example.capstonec22_ps353.utils.SharedViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class PriceFragment : Fragment() {

    private val sharedViewModel: SharedViewModel by activityViewModels()

    //    private lateinit var lineChart: LineChart
//    private var priceList = ArrayList<PriceList>()

    //    private lateinit var lineList: ArrayList<Entry>
//    private lateinit var lineData: LineData

    private var _binding: FragmentPriceBinding? = null
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

        setupTabLayout()

        val text = binding.autoCompleteTV.text.toString()
        sharedViewModel.setTitlePrice(text)

//        lineChart = binding.lineChart

//        initLineChart()
//        setDataLineChart()

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

    private fun setupTabLayout() {
        binding.apply {

            val fragmentBeras = mutableListOf<Fragment>(
                DetailCategoryFragment.newInstance(DetailCategoryFragment.BERAS1),
                DetailCategoryFragment.newInstance(DetailCategoryFragment.BERAS2),
                DetailCategoryFragment.newInstance(DetailCategoryFragment.BERAS3)
            )

            val fragmentBawangM = mutableListOf<Fragment>(
                DetailCategoryFragment.newInstance(DetailCategoryFragment.BAWANGMERAH)
            )

            val fragmentBawangP = mutableListOf<Fragment>(
                DetailCategoryFragment.newInstance(DetailCategoryFragment.BAWANGPUTIH)
            )

            val fragmentCabaiM = mutableListOf<Fragment>(
                DetailCategoryFragment.newInstance(DetailCategoryFragment.CABAIM1),
                DetailCategoryFragment.newInstance(DetailCategoryFragment.CABAIM2)
            )

            val fragmentCabaiR = mutableListOf<Fragment>(
                DetailCategoryFragment.newInstance(DetailCategoryFragment.CABAIR1),
                DetailCategoryFragment.newInstance(DetailCategoryFragment.CABAIR2)
            )

            val fragmentTitleBeras = mutableListOf(
                getString(R.string.beras1),
                getString(R.string.beras2),
                getString(R.string.beras3)
            )

            val fragmentTitleBawangM = mutableListOf(
                getString(R.string.bawangM)
            )

            val fragmentTitleBawangP = mutableListOf(
                getString(R.string.bawangP)
            )

            val fragmentTitleCabaiM = mutableListOf(
                getString(R.string.cabaiM1),
                getString(R.string.cabaiM2)
            )

            val fragmentTitleCabaiR = mutableListOf(
                getString(R.string.cabaiR1),
                getString(R.string.cabaiR2)
            )



            when (autoCompleteTV.text.toString()) {
                "Beras" -> {
                    viewPagerCategory.adapter =
                        SectionPagerAdapter(requireActivity(), fragmentBeras)
                    TabLayoutMediator(tbCategory, viewPagerCategory) { tab, position ->
                        tab.text = fragmentTitleBeras[position]
                    }.attach()

                    tbCategory.addOnTabSelectedListener(object :
                        TabLayout.OnTabSelectedListener {
                        override fun onTabSelected(tab: TabLayout.Tab?) {
                            when (tab?.position) {
                                DetailCategoryFragment.BERAS1 -> {
                                    sharedViewModel.setTitle(getString(R.string.beras1))
                                }

                                DetailCategoryFragment.BERAS2 -> {
                                    sharedViewModel.setTitle(getString(R.string.beras2))
                                }

                                DetailCategoryFragment.BERAS3 -> {
                                    sharedViewModel.setTitle(getString(R.string.beras3))
                                }
                            }
                        }

                        override fun onTabUnselected(tab: TabLayout.Tab?) {}
                        override fun onTabReselected(tab: TabLayout.Tab?) {}
                    })
                    sharedViewModel.setTitle(getString(R.string.beras1))
                }

                "Bawang Merah" -> {
                    viewPagerCategory.adapter =
                        SectionPagerAdapter(requireActivity(), fragmentBawangM)
                    TabLayoutMediator(tbCategory, viewPagerCategory) { tab, position ->
                        tab.text = fragmentTitleBawangM[position]
                    }.attach()

                    sharedViewModel.setTitle(getString(R.string.bawangM))
                }

                "Bawang Putih" -> {
                    viewPagerCategory.adapter =
                        SectionPagerAdapter(requireActivity(), fragmentBawangP)
                    TabLayoutMediator(tbCategory, viewPagerCategory) { tab, position ->
                        tab.text = fragmentTitleBawangP[position]
                    }.attach()

                    sharedViewModel.setTitle(getString(R.string.bawangP))
                }

                "Cabai Merah" -> {
                    viewPagerCategory.adapter =
                        SectionPagerAdapter(requireActivity(), fragmentCabaiM)
                    TabLayoutMediator(tbCategory, viewPagerCategory) { tab, position ->
                        tab.text = fragmentTitleCabaiM[position]
                    }.attach()

                    tbCategory.addOnTabSelectedListener(object :
                        TabLayout.OnTabSelectedListener {
                        override fun onTabSelected(tab: TabLayout.Tab?) {
                            when (tab?.position) {
                                DetailCategoryFragment.CABAIM1 -> {
                                    sharedViewModel.setTitle(getString(R.string.cabaiM1))
                                }

                                DetailCategoryFragment.CABAIM2 -> {
                                    sharedViewModel.setTitle(getString(R.string.cabaiM2))
                                }
                            }
                        }

                        override fun onTabUnselected(tab: TabLayout.Tab?) {}
                        override fun onTabReselected(tab: TabLayout.Tab?) {}
                    })
                    sharedViewModel.setTitle(getString(R.string.cabaiM1))
                }

                "Cabai Rawit" -> {
                    viewPagerCategory.adapter =
                        SectionPagerAdapter(requireActivity(), fragmentCabaiR)
                    TabLayoutMediator(tbCategory, viewPagerCategory) { tab, position ->
                        tab.text = fragmentTitleCabaiR[position]
                    }.attach()

                    tbCategory.addOnTabSelectedListener(object :
                        TabLayout.OnTabSelectedListener {
                        override fun onTabSelected(tab: TabLayout.Tab?) {
                            when (tab?.position) {
                                DetailCategoryFragment.CABAIR1 -> {
                                    sharedViewModel.setTitle(getString(R.string.cabaiR1))
                                }

                                DetailCategoryFragment.CABAIR2 -> {
                                    sharedViewModel.setTitle(getString(R.string.cabaiR2))
                                }
                            }
                        }

                        override fun onTabUnselected(tab: TabLayout.Tab?) {}
                        override fun onTabReselected(tab: TabLayout.Tab?) {}
                    })
                    sharedViewModel.setTitle(getString(R.string.cabaiR1))

                }
            }

        }
    }

//    private fun setDataLineChart() {
//        val entries: ArrayList<Entry> = ArrayList()
//
//        priceList = getPriceList()
//
//        for (i in priceList.indices) {
//            val price = priceList[i]
//            entries.add(Entry(i.toFloat(), price.PriceY.toFloat()))
//        }
//
//        val lineDataSet = LineDataSet(entries, "")
//
//        val data = LineData(lineDataSet)
//        lineChart.data = data
//        lineChart.invalidate()
//
//        lineDataSet.apply {
//            color = activity?.let { ContextCompat.getColor(it, R.color.label_primer) }!!
//            lineWidth = 2f
//            mode = LineDataSet.Mode.HORIZONTAL_BEZIER
//            setDrawValues(false)
//            setDrawCircles(false)
//
//            setDrawHorizontalHighlightIndicator(true)
//
//        }
//
//
//    }
//
//    private fun initLineChart() {
//        lineChart.apply {
//            xAxis.apply {
//                setDrawGridLines(false)
//                valueFormatter = AxisFormatter()
//                position = XAxis.XAxisPosition.BOTTOM
//                textSize = 12f
//            }
//            isAutoScaleMinMaxEnabled = true
//
//            axisRight.isEnabled = false
//            axisLeft.setDrawAxisLine(false)
//            axisLeft.gridLineWidth = 0.9f
//            axisLeft.setDrawGridLinesBehindData(true)
//            axisLeft.granularity = 6f
//            axisLeft.enableGridDashedLine(20f, 20f, 5f)
//            description = null
//
//        }
//
//
//        lineChart.legend.isEnabled = false
//        lineChart.axisRight.isEnabled = false
//
//        lineChart.description = null
//    }
//
//    inner class AxisFormatter : IndexAxisValueFormatter() {
//        override fun getAxisLabel(value: Float, axis: AxisBase?): String {
//            val index = value.toInt()
//            return if (index < priceList.size) {
//                priceList[index].labelX
//            } else {
//                ""
//            }
//        }
//    }

    override fun onResume() {
        super.onResume()
        setDropdown()
        setupTabLayout()
    }

    private fun setDropdown() {
        val category = resources.getStringArray(R.array.data_category)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.item_dropdown, category)
        binding.autoCompleteTV.setAdapter(arrayAdapter)
    }

//    private fun getPriceList(): ArrayList<PriceList> {
//        priceList.add(PriceList("Sen", 10000))
//        priceList.add(PriceList("Sel", 10500))
//        priceList.add(PriceList("Rab", 10200))
//        priceList.add(PriceList("Kam", 14000))
//        priceList.add(PriceList("Jum", 11400))
//        priceList.add(PriceList("Sab", 11100))
//        priceList.add(PriceList("Min", 12000))
//
//        return priceList
//    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}