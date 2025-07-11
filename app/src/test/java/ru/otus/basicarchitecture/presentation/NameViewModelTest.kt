package ru.otus.basicarchitecture.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import ru.otus.basicarchitecture.domain.UserName

class NameViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val wizardCache: WizardCache = mock()
    private val viewModel = NameViewModel(wizardCache)

    @Before
    fun before(){
        println("Начинается тест")
    }

    @After
    fun after(){
        println("Тест закончился")
    }

    @Test
    fun `empty name returns error`() {
        runTest {
            whenever(wizardCache.userName).thenReturn(UserName("", "Иванов", "19.09.1999"))
            viewModel.validateData()
            val actual = viewModel.errorEmptyName.value ?: throw RuntimeException("errorEmptyName == null")
            assertTrue(actual)
        }
    }

    @Test
    fun `empty surname returns error`() {
        runTest {
            whenever(wizardCache.userName).thenReturn(UserName("Иван", "", "19.09.1999"))
            viewModel.validateData()
            val actual = viewModel.errorEmptySurname.value ?: throw RuntimeException("errorEmptySurname == null")
            assertTrue(actual)
        }
    }

    @Test
    fun `empty birthday returns error`() {
        runTest {
            whenever(wizardCache.userName).thenReturn(UserName("Иван", "Иванов", ""))
            viewModel.validateData()
            val actual = viewModel.errorEmptyBirthday.value ?: throw RuntimeException("errorEmptyBirthday == null")
            assertTrue(actual)
        }
    }

    @Test
    fun `young age returns error`() {
        runTest {
            whenever(wizardCache.userName).thenReturn(UserName("Иван", "Иванов", "19.09.2009"))
            viewModel.validateData()
            val actual = viewModel.errorAge.value ?: throw RuntimeException("errorAge == null")
            assertTrue(actual)
        }
    }

    @Test
    fun `incorrect birthday entry returns error`() {
        runTest {
            whenever(wizardCache.userName).thenReturn(UserName("Иван", "Иванов", "19.09/2009"))
            viewModel.validateData()
            val actual = viewModel.errorBirthday.value ?: throw RuntimeException("errorBirthday == null")
            assertTrue(actual)
        }
    }

    @Test
    fun `valid input returns success`() {
        runTest {
            whenever(wizardCache.userName).thenReturn(UserName("Иван", "Иванов", "19.09.1999"))
            viewModel.validateData()
            val actualList = listOf<Boolean?>(
                viewModel.errorEmptyName.value,
                viewModel.errorEmptySurname.value,
                viewModel.errorEmptyBirthday.value,
                viewModel.errorBirthday.value,
                viewModel.errorAge.value,
            )
            if (actualList.contains(null)) {
                throw RuntimeException("actual == null")
            }
            val actual = actualList.contains(true)
            assertFalse(actual)
        }
    }
}