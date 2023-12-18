package com.example.pract2_2_v3_grebenukov.presentation.Data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [HealthData::class], version = 1)
abstract class HealthDataDatabase : RoomDatabase() {
    abstract fun healthDataDao(): HealthDataDao

    companion object {
        @Volatile
        private var INSTANCE: HealthDataDatabase? = null

        fun getDatabase(context: Context): HealthDataDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HealthDataDatabase::class.java,
                    "health_data_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}