package com.example.habittracker.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "habits")
data class Habit(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    /** Общее время в минутах, которое пользователь отметил по этой привычке */
    val totalMinutes: Long = 0
)
