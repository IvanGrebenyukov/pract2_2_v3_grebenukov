package com.example.pract2_2_v3_grebenukov.presentation.Recommendations

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pract2_2_v3_grebenukov.R
import com.example.pract2_2_v3_grebenukov.databinding.ActivityRecommendationsBinding

class RecommendationsActivity : Activity() {
    private lateinit var binding: ActivityRecommendationsBinding
    private lateinit var recommendationsAdapter: RecommendationsAdapter
    private lateinit var recommendationsList: MutableList<RecommendationItem> // Список рекомендаций
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecommendationsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        recommendationsList = mutableListOf(
            RecommendationItem(
                "Контролируйте давление",
                "Идеальные показатели давления – 120/80 мм рт.ст. для взрослых. Регулярно контролируйте давление."
            ),
            RecommendationItem(
                "Здоровый сон",
                "Взрослым рекомендуется спать 7-9 часов в сутки для поддержания здоровья и энергии."
            ),
            RecommendationItem(
                "Физическая активность",
                "Выполняйте умеренные физические упражнения в течение 30 минут каждый день для укрепления здоровья сердца и тела."
            ),
            RecommendationItem(
                "Правильное питание",
                "Употребляйте разнообразную диету, богатую фруктами, овощами и злаками. Ограничьте потребление жиров и сахаров."
            ),
            RecommendationItem(
                "Регулярные медицинские осмотры",
                "Проводите регулярные осмотры у врача для контроля здоровья и предотвращения возможных заболеваний."
            ),
            RecommendationItem(
                "Поддерживайте уровень глюкозы в крови",
                "Контролируйте уровень глюкозы в крови, особенно при риске диабета."
            ),
            RecommendationItem(
                "Отказ от вредных привычек",
                "Избегайте курения и употребления алкоголя или умерьте их потребление до минимума."
            ),
            RecommendationItem(
                "Психологическое здоровье",
                "Поддерживайте эмоциональное здоровье, уделяйте время отдыху, медитации или занятиям, которые вам нравятся."
            )
        )


        binding.recyclerViewRecommendations.layoutManager = LinearLayoutManager(this)
        recommendationsAdapter = RecommendationsAdapter(recommendationsList)
        binding.recyclerViewRecommendations.adapter = recommendationsAdapter
    }
}