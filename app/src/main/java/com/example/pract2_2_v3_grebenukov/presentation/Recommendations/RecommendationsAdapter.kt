package com.example.pract2_2_v3_grebenukov.presentation.Recommendations

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pract2_2_v3_grebenukov.R

class RecommendationsAdapter(private val recommendationsList: List<RecommendationItem>) :
    RecyclerView.Adapter<RecommendationsAdapter.RecommendationViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendationViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_recommendation, parent, false)
        return RecommendationViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecommendationViewHolder, position: Int) {
        val currentItem = recommendationsList[position]

        holder.textViewTitle.text = currentItem.title
        holder.textViewDescription.text = currentItem.description
    }

    override fun getItemCount(): Int {
        return recommendationsList.size
    }

    inner class RecommendationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewTitle: TextView = itemView.findViewById(R.id.textViewRecommendationTitle)
        val textViewDescription: TextView = itemView.findViewById(R.id.textViewRecommendationDescription)
    }
}