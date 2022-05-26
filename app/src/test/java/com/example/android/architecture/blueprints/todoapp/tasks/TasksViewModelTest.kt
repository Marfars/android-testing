package com.example.android.architecture.blueprints.todoapp.tasks

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.android.architecture.blueprints.todoapp.R
import com.example.android.architecture.blueprints.todoapp.data.Task
import com.example.android.architecture.blueprints.todoapp.fakerepository.FakeTaskRepository
import com.example.android.architecture.blueprints.todoapp.getOrAwaitValue
import org.hamcrest.Matchers.*
import org.junit.Assert
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

class TasksViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var fakeTaskRepository: FakeTaskRepository

    private lateinit var viewModel: TasksViewModel

    @Before
    fun initViewModel() {
        fakeTaskRepository = FakeTaskRepository()
        val task1 = Task("Title1", "Description1")
        val task2 = Task("Title2", "Description2", true)
        val task3 = Task("Title3", "Description3", true)
        fakeTaskRepository.addTasks(task1, task2, task3)
        viewModel = TasksViewModel(fakeTaskRepository)
    }

    @Test
    fun addNewTask_setsNewTaskEvent() {
        viewModel.addNewTask()
        val value = viewModel.newTaskEvent.getOrAwaitValue()
        assertThat(value.getContentIfNotHandled(), (not(nullValue())))
    }

    @Test
    fun setFilterAllTasks_tasksAddViewVisible() {
        viewModel.setFiltering(TasksFilterType.ALL_TASKS)
        val value = viewModel.tasksAddViewVisible.getOrAwaitValue()
        assertThat(value, `is`(true))
        val currentLabel = viewModel.currentFilteringLabel.getOrAwaitValue()
        assertThat(currentLabel, `is`(R.string.label_all))
    }

}