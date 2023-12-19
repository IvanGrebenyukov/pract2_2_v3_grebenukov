package com.example.pract2_2_v3_grebenukov.presentation

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pract2_2_v3_grebenukov.R
import com.example.pract2_2_v3_grebenukov.databinding.ActivityLogInBinding
import com.example.pract2_2_v3_grebenukov.databinding.ActivityMainBinding
import com.example.pract2_2_v3_grebenukov.presentation.Recommendations.RecommendationsActivity

class MainActivity : Activity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnToListActivity.setOnClickListener {
            val intent = Intent(this, HealthListInfoActivity::class.java)
            startActivity(intent)
        }
        binding.btnAddInfo.setOnClickListener {
            val intent = Intent(this, AddHealthInfoActivity::class.java)
            startActivity(intent)
        }
        binding.btnToRecommendationsActivity.setOnClickListener {
            val intent = Intent(this, RecommendationsActivity::class.java)
            startActivity(intent)
        }
    }
}