package com.example.pract2_2_v3_grebenukov.presentation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pract2_2_v3_grebenukov.R
import com.example.pract2_2_v3_grebenukov.databinding.ActivityHealthListInfoBinding
import com.example.pract2_2_v3_grebenukov.presentation.Data.HealthData
import com.example.pract2_2_v3_grebenukov.presentation.Data.HealthDataDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HealthListInfoActivity : Activity() {
    private lateinit var binding: ActivityHealthListInfoBinding
    private lateinit var healthDataAdapter: HealthDataAdapter
    private lateinit var healthDataList: MutableList<HealthData>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHealthListInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        loadHealthData()
    }
    private fun loadHealthData(){
        GlobalScope.launch(Dispatchers.IO) {
            val healthDataList = HealthDataDatabase.getDatabase(this@HealthListInfoActivity)
                .healthDataDao().getAllHealthData()
            launch(Dispatchers.Main) {
                displayHealthData(healthDataList)
            }
        }
    }
    private fun displayHealthData(healthDataList: List<HealthData>){
        healthDataAdapter = HealthDataAdapter(healthDataList,
            editClickListener = {healthData ->
                val intent = Intent(this@HealthListInfoActivity, AddHealthInfoActivity::class.java)
                intent.putExtra("HEALTH_DATA_ID", healthData.id)
                startActivity(intent)
            },
            deleteClickListener = {healthData ->

            }
            )
        binding.recyclerView.adapter = healthDataAdapter
    }
}