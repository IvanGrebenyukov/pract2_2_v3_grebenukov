package com.example.pract2_2_v3_grebenukov.presentation.Data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "health_data")
data class HealthData(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val date: String,
    val systolicPressure: Int,
    val diastolicPressure: Int,
    val calories: Int,
    val sleepDuration: Int
)