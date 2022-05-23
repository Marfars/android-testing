package com.example.android.architecture.blueprints.todoapp.statistics

import com.example.android.architecture.blueprints.todoapp.data.Task
import org.junit.Assert.*
import org.junit.Test

class StatisticsUtilsTest {

    @Test
    fun getActiveAndCompletedStats_noCompleted_returnsHundredZero() {
        val tasks = listOf(Task("title", "desc",false))
        val result = getActiveAndCompletedStats(tasks)
//        assertEquals(result.completedTasksPercent, 0.0.toFloat())
        assertEquals(result.activeTasksPercent, 100.0.toFloat())
    }
}