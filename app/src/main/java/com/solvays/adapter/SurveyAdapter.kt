package com.solvays.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import com.solvays.model.Survey

class SurveyAdapter(context: Context, private val surveys: List<Survey>) :
    ArrayAdapter<Survey>(context, android.R.layout.simple_list_item_2, surveys), Filterable {

    private var originalSurveys = ArrayList<Survey>(surveys)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        if (view == null) {
            view = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_2, parent, false)
        }

        val text1 = view!!.findViewById<View>(android.R.id.text1) as TextView
        val text2 = view.findViewById<View>(android.R.id.text2) as TextView
        text1.text = surveys[position].appName
        val numQuestions = surveys[position].numQuestions
        text2.text = "${numQuestions} questions"
        return view
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filteredSurveys = if (constraint.isNullOrBlank()) {
                    originalSurveys
                } else {
                    originalSurveys.filter { it.appName.contains(constraint, ignoreCase = true) }
                }

                return FilterResults().apply {
                    values = filteredSurveys
                    count = filteredSurveys.size
                }
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                @Suppress("UNCHECKED_CAST")
                val filteredSurveys = results?.values as? List<Survey> ?: emptyList()

                clear()
                addAll(filteredSurveys)
                notifyDataSetChanged()
            }
        }
    }
}
