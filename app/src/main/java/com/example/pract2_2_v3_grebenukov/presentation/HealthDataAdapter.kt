package com.example.pract2_2_v3_grebenukov.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pract2_2_v3_grebenukov.R
import com.example.pract2_2_v3_grebenukov.presentation.Data.HealthData

class HealthDataAdapter(private val healthDataList: List<HealthData>) :
    RecyclerView.Adapter<HealthDataAdapter.HealthDataViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HealthDataViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_health_data, parent, false)
        return HealthDataViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: HealthDataViewHolder, position: Int) {
        val currentItem = healthDataList[position]

        holder.textViewDate.text = currentItem.date
        holder.textViewPressure.text =
            "Давление: ${currentItem.systolicPressure}/${currentItem.diastolicPressure}"
        holder.textViewCalories.text = "Калории: ${currentItem.calories}"
        holder.textViewSleepDuration.text = "Сон: ${currentItem.sleepDuration} часов"
    }

    override fun getItemCount(): Int {
        return healthDataList.size
    }

    inner class HealthDataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewDate: TextView = itemView.findViewById(R.id.textViewDate)
        val textViewPressure: TextView = itemView.findViewById(R.id.textViewPressure)
        val textViewCalories: TextView = itemView.findViewById(R.id.textViewCalories)
        val textViewSleepDuration: TextView = itemView.findViewById(R.id.textViewSleepDuration)
        val imageButtonEdit: ImageButton = itemView.findViewById(R.id.imageButtonEdit)
        val imageButtonDelete: ImageButton = itemView.findViewById(R.id.imageButtonDelete)
    }
}