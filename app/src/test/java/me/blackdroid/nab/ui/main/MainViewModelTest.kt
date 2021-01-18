package me.blackdroid.nab.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import io.reactivex.Observable
import me.blackdroid.nab.model.ForeCast
import me.blackdroid.nab.repo.NabRepo
import me.blackdroid.nab.util.ImmediateSchedulerProvider
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.*


class MainViewModelTest {

    private var immediateSchedulerProvider = ImmediateSchedulerProvider()

    private lateinit var mainViewModel : MainViewModel
    private lateinit var message: MutableLiveData<String>

    var nabRepo: NabRepo = mock(NabRepo::class.java)

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        mainViewModel = MainViewModel(
            nabRepo = nabRepo,
            schedulerProvider = immediateSchedulerProvider
        )
        message = mainViewModel.message
    }

    @Test
    fun testLengthOfQuery() {
        mainViewModel.query.value = "ha"
        mainViewModel.loadDaily()
        assertMessage("Please enter at least 3 digit")

        mainViewModel.query.value = "ha noi"
        `when`(nabRepo.getDaily(ArgumentMatchers.anyString())).thenReturn(Observable.empty())
        mainViewModel.loadDaily()
        assertMessage("")
    }

    @Test
    fun testLoadDataSuccess() {
        mainViewModel.query.value = "ha noi"
        val forecast =  ForeCast(query = "ha noi", id = 1)
        `when`(nabRepo.getDaily(ArgumentMatchers.anyString()))
            .thenReturn(Observable.just(forecast))
        mainViewModel.loadDaily()

        assertMessage("")
        assertEquals(forecast, mainViewModel.foreCast.value)
    }

    @Test
    fun testLoadDataFailure() {
        mainViewModel.query.value = "ha noi"
        val forecast =  ForeCast(query = "ha noi", id = 1)
        `when`(nabRepo.getDaily(ArgumentMatchers.anyString()))
            .thenReturn(Observable.error(Throwable("Http Error")))
        mainViewModel.loadDaily()

        assertMessage("Http Error")
        assertEquals(null, mainViewModel.foreCast.value)
    }

    private fun assertMessage(expected: String?) {
        assertEquals(expected, message.value)
    }
}