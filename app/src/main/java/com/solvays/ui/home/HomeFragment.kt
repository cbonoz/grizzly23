package com.solvays.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.solvays.MainActivity
import com.solvays.R
import com.solvays.adapter.SurveyAdapter
import com.solvays.databinding.FragmentHomeBinding
import com.solvays.model.Survey
import com.solvays.util.SurveyHelper.Companion.getDemoAppNames


class HomeFragment : Fragment() {
    private lateinit var searchView: SearchView
    private lateinit var listView: ListView
    private lateinit var adapter: SurveyAdapter
    private lateinit var boxList: ArrayList<Survey>

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        searchView = root.findViewById(R.id.search_view)
        listView = root.findViewById(R.id.list_view)

        // Set up the list root

        // Set up the list root
        boxList = ArrayList()
        boxList.addAll(getDemoAppNames())

        adapter = SurveyAdapter(context as Context, boxList)
        listView.adapter = adapter

        // Set up the search bar
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return false
            }
        })

        // Set up item click listener

        // Set up item click listener
        listView.setOnItemClickListener { adapterView, root, i, l ->
            val selected: Survey = adapter.getItem(i) as Survey
            val bundle = bundleOf(
                "appName" to selected.appName,
                "numQuestions" to selected.numQuestions
            )
            (activity as MainActivity).navigateTo(R.id.nav_survey, bundle)
        }

        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}