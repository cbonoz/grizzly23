package com.solvays.ui.result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.solvays.R
import com.solvays.databinding.FragmentResultsBinding


class ResultsFragment : Fragment() {

    private lateinit var barChart: BarChart
    private var _binding: FragmentResultsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentResultsBinding.inflate(inflater, container, false)
        val view: View = binding.root

        barChart = view.findViewById(R.id.bar_chart);

        showBarChart()

        return view
    }

    private fun showBarChart() {
        val valueList = ArrayList<Float>()
        val entries: ArrayList<BarEntry> = ArrayList()
        val title = "Survey Attempts"

        //input data
        for (i in 0..5) {
            valueList.add((i * 100.1).toFloat())
        }

        //fit the data into a bar
        for (i in 0 until valueList.size) {
            val barEntry = BarEntry(i.toFloat(), valueList[i])
            entries.add(barEntry)
        }
        val barDataSet = BarDataSet(entries, title)
        val data = BarData(barDataSet)
        barChart.data = data
        barChart.invalidate()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}