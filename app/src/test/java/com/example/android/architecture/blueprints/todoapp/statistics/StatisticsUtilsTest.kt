package com.example.android.architecture.blueprints.todoapp.statistics

import com.example.android.architecture.blueprints.todoapp.data.Task
import org.junit.Assert.*
import org.junit.Test
import org.hamcrest.CoreMatchers.`is`

class StatisticsUtilsTest {

    @Test
    fun getActiveAndCompletedStats_noCompleted_returnsHundredZero() {
        val tasks = listOf(Task("title", "desc",false))
        val result = getActiveAndCompletedStats(tasks)
        assertThat(result.completedTasksPercent, `is`(0f))
        assertThat(result.activeTasksPercent, `is`(100f))
    }

    @Test
    fun getActiveAndCompletedStats_list_null() {
        val tasks = null
        val res = getActiveAndCompletedStats(tasks)
        assertThat(res.completedTasksPercent, `is`(0f))
        assertThat(res.activeTasksPercent, `is`(0f))
    }

    @Test
    fun getActiveAndCompletedStats_list_empty() {
        val tasks = emptyList<Task>()
        val res = getActiveAndCompletedStats(tasks)
        assertThat(res.completedTasksPercent, `is`(0f))
        assertThat(res.activeTasksPercent, `is`(0f))
    }

}