package com.example.habittracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.habittracker.ui.HabitScreen
import com.example.habittracker.ui.theme.HabitTrackerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HabitTrackerTheme {
                val viewModel: HabitViewModel = viewModel()
                val habits by viewModel.habits.collectAsState()

                HabitScreen(
                    habits = habits,
                    onAddHabit = viewModel::addHabit,
                    onAddTime = viewModel::addTimeToHabit,
                    onDeleteHabit = viewModel::deleteHabit
                )
            }
        }
    }
}