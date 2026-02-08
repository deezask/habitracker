package com.example.habittracker

/**
 * Форматирует количество минут в читаемый вид: "X ч Y мин" или "Y мин".
 */
fun formatTimeSpent(totalMinutes: Long): String {
    if (totalMinutes <= 0) return "0 мин"
    val hours = totalMinutes / 60
    val minutes = totalMinutes % 60
    return when {
        hours > 0 && minutes > 0 -> "${hours} ч ${minutes} мин"
        hours > 0 -> "${hours} ч"
        else -> "${minutes} мин"
    }
}
