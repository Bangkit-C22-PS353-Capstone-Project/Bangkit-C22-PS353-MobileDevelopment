package com.example.capstonec22_ps353.ui.price

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
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
    private val priceViewModel: PriceViewModel by activityViewModels()

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
        binding.autoCompleteTV.setOnItemClickListener { adapterView, _, i, _ ->
            val item = adapterView.getItemAtPosition(i).toString()
            sharedViewModel.setTitlePrice(item)
            setDropdown()
            setupTabLayout()
            Toast.makeText(activity, item, Toast.LENGTH_SHORT).show()
        }
    }

//    override fun onViewStateRestored(savedInstanceState: Bundle?) {
//        super.onViewStateRestored(savedInstanceState)
//        setupTabLayout()
//    }

    private fun setupTabLayout() {
        binding.apply {

            val fragmentBeras = mutableListOf<Fragment>(
                DetailPriceFragment.newInstance(DetailPriceFragment.BERAS3),
                DetailPriceFragment.newInstance(DetailPriceFragment.BERAS2),
                DetailPriceFragment.newInstance(DetailPriceFragment.BERAS1),
            )

            val fragmentBawangM = mutableListOf<Fragment>(
                DetailPriceFragment.newInstance(DetailPriceFragment.BAWANGMERAH)
            )

            val fragmentBawangP = mutableListOf<Fragment>(
                DetailPriceFragment.newInstance(DetailPriceFragment.BAWANGPUTIH)
            )

            val fragmentCabaiM = mutableListOf<Fragment>(
                DetailPriceFragment.newInstance(DetailPriceFragment.CABAIM1),
                DetailPriceFragment.newInstance(DetailPriceFragment.CABAIM2)
            )

            val fragmentCabaiR = mutableListOf<Fragment>(
                DetailPriceFragment.newInstance(DetailPriceFragment.CABAIR1),
                DetailPriceFragment.newInstance(DetailPriceFragment.CABAIR2)
            )

            val fragmentTitleBeras = mutableListOf(
                getString(R.string.beras3),
                getString(R.string.beras2),
                getString(R.string.beras1),

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

            sharedViewModel.titlePrice.observe(viewLifecycleOwner) {
                when (val category = it) {
                    "Beras" -> {
                        viewPagerCategory.adapter =
                            SectionPagerAdapter(requireActivity(), fragmentBeras)
                        TabLayoutMediator(tbPrice, viewPagerCategory) { tab, position ->
                            tab.text = fragmentTitleBeras[position]
                        }.attach()

                        tbPrice.addOnTabSelectedListener(object :
                            TabLayout.OnTabSelectedListener {
                            override fun onTabSelected(tab: TabLayout.Tab?) {
//                                Toast.makeText(activity, tab?.text.toString(), Toast.LENGTH_SHORT).show()
                                when (tab?.position) {
                                    DetailPriceFragment.BERAS3 -> {
//                                        sharedViewModel.setTitle(getString(R.string.beras3))
                                        sharedViewModel.setTitle(category)
                                    }

                                    DetailPriceFragment.BERAS2 -> {
                                        Toast.makeText(activity, category, Toast.LENGTH_SHORT)
                                            .show()
//                                        sharedViewModel.setTitle(getString(R.string.beras2))
                                        sharedViewModel.setTitle(category)
                                    }

                                    DetailPriceFragment.BERAS1 -> {
                                        Toast.makeText(activity, category, Toast.LENGTH_SHORT)
                                            .show()
//                                        sharedViewModel.setTitle(getString(R.string.beras1))
                                        sharedViewModel.setTitle(category)
                                    }

                                }
                            }

                            override fun onTabUnselected(tab: TabLayout.Tab?) {

                            }

                            override fun onTabReselected(tab: TabLayout.Tab?) {

                            }
                        })
//                        sharedViewModel.setTitle(getString(R.string.beras1))
                        sharedViewModel.setTitle(category)
                    }

                    "Bawang Merah" -> {
                        viewPagerCategory.adapter =
                            SectionPagerAdapter(requireActivity(), fragmentBawangM)
                        TabLayoutMediator(tbPrice, viewPagerCategory) { tab, position ->
                            tab.text = fragmentTitleBawangM[position]
                        }.attach()

                        sharedViewModel.setTitle(getString(R.string.bawangM))
                    }

                    "Bawang Putih" -> {
                        viewPagerCategory.adapter =
                            SectionPagerAdapter(requireActivity(), fragmentBawangP)
                        TabLayoutMediator(tbPrice, viewPagerCategory) { tab, position ->
                            tab.text = fragmentTitleBawangP[position]
                        }.attach()

                        sharedViewModel.setTitle(getString(R.string.bawangP))
                    }

                    "Cabai Merah" -> {
                        viewPagerCategory.adapter =
                            SectionPagerAdapter(requireActivity(), fragmentCabaiM)
                        TabLayoutMediator(tbPrice, viewPagerCategory) { tab, position ->
                            tab.text = fragmentTitleCabaiM[position]
                        }.attach()

                        tbPrice.addOnTabSelectedListener(object :
                            TabLayout.OnTabSelectedListener {
                            val s = category
                            override fun onTabSelected(tab: TabLayout.Tab?) {
//                                Toast.makeText(activity, tab?.text.toString(), Toast.LENGTH_SHORT).show()
                                when (tab?.position) {
                                    DetailPriceFragment.CABAIM1 -> {
//                                        sharedViewModel.setTitle(getString(R.string.cabaiM1))
                                        sharedViewModel.setTitle(s)
                                    }

                                    DetailPriceFragment.CABAIM2 -> {
//                                        val s = category
//                                        sharedViewModel.setTitle(getString(R.string.cabaiM2))
                                        sharedViewModel.setTitle(s)
                                    }
                                }
                            }

                            override fun onTabUnselected(tab: TabLayout.Tab?) {}
                            override fun onTabReselected(tab: TabLayout.Tab?) {}
                        })
//                        sharedViewModel.setTitle(getString(R.string.cabaiM1))
                        sharedViewModel.setTitle(category)
                    }

                    "Cabai Rawit" -> {
                        viewPagerCategory.adapter =
                            SectionPagerAdapter(requireActivity(), fragmentCabaiR)
                        TabLayoutMediator(tbPrice, viewPagerCategory) { tab, position ->
                            tab.text = fragmentTitleCabaiR[position]
                        }.attach()

                        tbPrice.addOnTabSelectedListener(object :
                            TabLayout.OnTabSelectedListener {
                            override fun onTabSelected(tab: TabLayout.Tab?) {
//                                Toast.makeText(activity, tab?.text.toString(), Toast.LENGTH_SHORT).show()
                                when (tab?.position) {
                                    DetailPriceFragment.CABAIR1 -> {
                                        sharedViewModel.setTitle(getString(R.string.cabaiR1))
                                    }

                                    DetailPriceFragment.CABAIR2 -> {
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