package com.example.pract2_2_v3_grebenukov.presentation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pract2_2_v3_grebenukov.R
import com.example.pract2_2_v3_grebenukov.databinding.ActivityHealthListInfoBinding
import com.example.pract2_2_v3_grebenukov.presentation.Data.HealthData
import com.example.pract2_2_v3_grebenukov.presentation.Data.HealthDataDao
import com.example.pract2_2_v3_grebenukov.presentation.Data.HealthDataDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HealthListInfoActivity : Activity() {
    private lateinit var binding: ActivityHealthListInfoBinding
    private lateinit var healthDataAdapter: HealthDataAdapter
    private lateinit var healthDataList: MutableList<HealthData>
    private lateinit var healthDataDao: HealthDataDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHealthListInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        healthDataDao = HealthDataDatabase.getDatabase(application).healthDataDao()
        healthDataList = mutableListOf()
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        healthDataAdapter = HealthDataAdapter(healthDataList)
        healthDataAdapter.editClickListener = { healthData ->
            val intent = Intent(this@HealthListInfoActivity, AddHealthInfoActivity::class.java)
            intent.putExtra("EDIT_MODE", true)
            intent.putExtra("HEALTH_DATA", healthData)
            startActivityForResult(intent, EDIT_REQUEST_CODE)
        }
        healthDataAdapter.deleteClickListener = { healthData ->
            GlobalScope.launch(Dispatchers.IO) {
                try {
                    healthDataDao.deleteHealthData(healthData)

                    runOnUiThread {
                        Toast.makeText(this@HealthListInfoActivity, "Данные успешно удалены", Toast.LENGTH_SHORT).show()
                        healthDataList.remove(healthData)
                        healthDataAdapter.notifyDataSetChanged()
                    }
                } catch (e: Exception) {
                    runOnUiThread {
                        Toast.makeText(this@HealthListInfoActivity, "Ошибка удаления данных", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        binding.recyclerView.adapter = healthDataAdapter
        loadHealthData()
    }
    private fun loadHealthData(){
        GlobalScope.launch(Dispatchers.IO) {
            val data = healthDataDao.getAllHealthData()
            runOnUiThread {
                healthDataList.clear()
                healthDataList.addAll(data)
                healthDataAdapter.notifyDataSetChanged()
            }
        }
    }
    override fun onResume() {
        super.onResume()
        loadHealthData()
    }

    companion object {
        const val EDIT_REQUEST_CODE = 1001
    }
}