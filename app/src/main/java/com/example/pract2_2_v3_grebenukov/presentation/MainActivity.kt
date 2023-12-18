package com.example.pract2_2_v3_grebenukov.presentation

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pract2_2_v3_grebenukov.R
import com.example.pract2_2_v3_grebenukov.databinding.ActivityLogInBinding
import com.example.pract2_2_v3_grebenukov.databinding.ActivityMainBinding

class MainActivity : Activity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}