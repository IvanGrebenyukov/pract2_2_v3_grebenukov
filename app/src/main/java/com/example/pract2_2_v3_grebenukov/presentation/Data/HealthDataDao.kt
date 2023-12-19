package com.example.pract2_2_v3_grebenukov.presentation.Data

import androidx.room.*

@Dao
interface HealthDataDao {
    @Query("SELECT * FROM health_data")
    suspend fun getAllHealthData(): MutableList<HealthData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHealthData(healthData: HealthData)

    @Update
    suspend fun updateHealthData(healthData: HealthData)

    @Delete
    suspend fun deleteHealthData(healthData: HealthData)

    @Query("SELECT * FROM health_data WHERE id = :id")
    suspend fun getHealthDataById(id: Int): HealthData?
}