package com.example.pract2_2_v3_grebenukov.presentation

import android.app.Activity
import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.room.Room
import com.example.pract2_2_v3_grebenukov.databinding.ActivityAddHealthInfoBinding
import com.example.pract2_2_v3_grebenukov.presentation.Data.HealthData
import com.example.pract2_2_v3_grebenukov.presentation.Data.HealthDataDao
import com.example.pract2_2_v3_grebenukov.presentation.Data.HealthDataDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale

class AddHealthInfoActivity : Activity() {
    private lateinit var binding: ActivityAddHealthInfoBinding
    private lateinit var healthDataDao: HealthDataDao
    private lateinit var healthData: HealthData
    private var isEditMode = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddHealthInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val healthDataDatabase = Room.databaseBuilder(
            applicationContext,
            HealthDataDatabase::class.java, "health_data_database"
        ).build()
        healthDataDao = healthDataDatabase.healthDataDao()
        binding.edDate.setOnClickListener{
            showDatePicker()
        }
        binding.btnAddInfo.setOnClickListener {
                onSaveHealthInfo()
        }
        if (intent.getBooleanExtra("EDIT_MODE", false)) {
            isEditMode = true
            healthData = intent.getSerializableExtra("HEALTH_DATA") as HealthData
            populateFieldsWithHealthData(healthData)
            binding.btnAddInfo.text = "Изменить данные"
        }
    }
    private fun showDatePicker(){
        val calendar = Calendar.getInstance()
        val dateSetListener = DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
            val newDate = Calendar.getInstance()
            newDate.set(year, monthOfYear, dayOfMonth)
            val dateFormatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            binding.edDate.setText(dateFormatter.format(newDate.time))
        }
        DatePickerDialog(
            this, dateSetListener, calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }
    fun onSaveHealthInfo(){
            binding.apply {
                val date = edDate.text.toString()
                val systolicPressure = edSystolicPressure.text.toString()
                val diastolicPressure = edDiastolicPressure.text.toString()
                val caloriesBurned = edCalorieBurned.text.toString()
                val caloriesReceived = edCalorieReceived.text.toString()
                val hoursSleep = edHoursSleep.text.toString()
                var calories = 0
                if(caloriesBurned.toInt() > caloriesReceived.toInt()){
                    calories = caloriesBurned.toInt() - caloriesReceived.toInt()
                }
                else{
                    calories = caloriesReceived.toInt() - caloriesBurned.toInt()
                }
                if(date.isNotEmpty() &&
                    systolicPressure.isNotEmpty() &&
                    diastolicPressure.isNotEmpty() &&
                    caloriesBurned.isNotEmpty() &&
                    caloriesReceived.isNotEmpty() &&
                    hoursSleep.isNotEmpty()){
                    if (isEditMode){
                        val updatedHealthData = HealthData(
                            id = healthData.id,
                            date = date,
                            systolicPressure = systolicPressure.toInt(),
                            diastolicPressure = diastolicPressure.toInt(),
                            calories = calories,
                            sleepDuration = hoursSleep.toInt()
                        )
                        GlobalScope.launch(Dispatchers.IO) {
                            try {
                                healthDataDao.updateHealthData(updatedHealthData)
                                runOnUiThread {
                                    Toast.makeText(this@AddHealthInfoActivity, "Данные успешно обновлены", Toast.LENGTH_SHORT).show()
                                    finish()
                                }
                            } catch (e: Exception) {
                                runOnUiThread {
                                    Toast.makeText(this@AddHealthInfoActivity, "Ошибка обновления данных", Toast.LENGTH_SHORT).show()
                                }
                            }
                        }
                    }
                    else{
                        val healthData = HealthData(
                            date = date,
                            systolicPressure = systolicPressure.toInt(),
                            diastolicPressure = diastolicPressure.toInt(),
                            calories = calories,
                            sleepDuration = hoursSleep.toInt()
                        )
                        GlobalScope.launch(Dispatchers.IO) {
                            try{
                                healthDataDao.insertHealthData(healthData)

                                runOnUiThread{
                                    Toast.makeText(this@AddHealthInfoActivity, "Данные успешно сохранены", Toast.LENGTH_SHORT).show()
                                    clearFields()
                                }
                            } catch (e: Exception){
                                runOnUiThread{
                                    Toast.makeText(this@AddHealthInfoActivity, "Ошибка при сохранении данных", Toast.LENGTH_SHORT).show()
                                }
                            }
                        }
                    }
                }
                else{
                    Toast.makeText(this@AddHealthInfoActivity, "Заполните все поля", Toast.LENGTH_SHORT).show()
                }
            }


    }
    private fun clearFields(){
        binding.apply {
            edDate.text.clear()
            edSystolicPressure.text.clear()
            edDiastolicPressure.text.clear()
            edCalorieBurned.text.clear()
            edCalorieReceived.text.clear()
            edHoursSleep.text.clear()
        }
    }

    private fun updateHealthDataInDatabase() {


    }
    private fun populateFieldsWithHealthData(healthData: HealthData) {
        binding.apply {
            binding.edDate.setText(healthData.date)
            binding.edSystolicPressure.setText(healthData.systolicPressure.toString())
            binding.edDiastolicPressure.setText(healthData.diastolicPressure.toString())
            binding.edCalorieBurned.setText("0")
            binding.edCalorieReceived.setText(healthData.calories.toString())
            binding.edHoursSleep.setText(healthData.sleepDuration.toString())
        }

    }
}