@file:Suppress("OPT_IN_IS_NOT_ENABLED")

package com.example.android.architecture.blueprints.todoapp.taskdetail

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import androidx.transition.R
import com.example.android.architecture.blueprints.todoapp.ServiceLocators
import com.example.android.architecture.blueprints.todoapp.data.Task
import com.example.android.architecture.blueprints.todoapp.data.source.TasksRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@OptIn(ExperimentalCoroutinesApi::class)
@MediumTest
@RunWith(AndroidJUnit4::class)
class TaskDetailFragmentTest {

    private lateinit var repository: TasksRepository

    @Before
    fun initRepository() {
        repository = FakeAndroidTestRepository()
        ServiceLocators.tasksRepository = repository
    }

    @Test
    fun activeTaskDetails_DisplayInUi() {
        runBlockingTest {
            val activeTask = Task("Active Task", "Androidx Rocks", false)
            repository.saveTask(activeTask)
            val bundle = TaskDetailFragmentArgs(activeTask.id).toBundle()
            launchFragmentInContainer<TaskDetailFragment>(bundle, R.style.Theme_AppCompat)
            Thread.sleep(2000)
        }
    }

    @After
    fun cleanupDb() = runBlockingTest {
        ServiceLocators.resetRepository()
    }

}