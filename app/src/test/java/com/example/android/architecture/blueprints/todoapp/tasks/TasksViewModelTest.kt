package com.example.android.architecture.blueprints.todoapp.tasks

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.android.architecture.blueprints.todoapp.R
import com.example.android.architecture.blueprints.todoapp.getOrAwaitValue
import org.hamcrest.Matchers.*
import org.junit.Assert
import org.junit.Assert.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TasksViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun addNewTask_setsNewTaskEvent() {
        val viewModel = TasksViewModel(ApplicationProvider.getApplicationContext())
        viewModel.addNewTask()
        val value = viewModel.newTaskEvent.getOrAwaitValue()
        assertThat(value.getContentIfNotHandled(), (not(nullValue())))
    }

    @Test
    fun setFilterAllTasks_tasksAddViewVisible() {

        // Given a fresh ViewModel
        val viewModel = TasksViewModel(ApplicationProvider.getApplicationContext())
        // When the filter type is ALL_TASKS
        viewModel.setFiltering(TasksFilterType.ALL_TASKS)
        // Then the "Add task" action is visible
        val value = viewModel.tasksAddViewVisible.getOrAwaitValue()
        assertThat(value, `is`(true))
        val currentLabel = viewModel.currentFilteringLabel.getOrAwaitValue()
        assertThat(currentLabel, `is`(R.string.label_all))
    }

}