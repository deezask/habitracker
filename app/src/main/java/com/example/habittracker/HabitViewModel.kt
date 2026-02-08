package com.example.habittracker

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.habittracker.data.AppDatabase
import com.example.habittracker.data.Habit
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HabitViewModel(application: Application) : AndroidViewModel(application) {

    private val dao = AppDatabase.getInstance(application).habitDao()

    val habits: StateFlow<List<Habit>> = dao.getAllHabits()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun addHabit(name: String) {
        if (name.isBlank()) return
        viewModelScope.launch {
            dao.insert(Habit(name = name.trim()))
        }
    }

    fun addTimeToHabit(habit: Habit, minutesToAdd: Long) {
        if (minutesToAdd <= 0) return
        viewModelScope.launch {
            dao.update(habit.copy(totalMinutes = habit.totalMinutes + minutesToAdd))
        }
    }

    fun setHabitTime(habit: Habit, newTotalMinutes: Long) {
        viewModelScope.launch {
            dao.update(habit.copy(totalMinutes = maxOf(0, newTotalMinutes)))
        }
    }

    fun deleteHabit(habit: Habit) {
        viewModelScope.launch {
            dao.delete(habit)
        }
    }
}
