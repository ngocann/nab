package me.blackdroid.nab.repo

import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import me.blackdroid.nab.data.local.ForecastDao
import me.blackdroid.nab.data.remote.NabService
import me.blackdroid.nab.model.ForeCast
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import org.mockito.Mockito.*
import java.util.*


class NabRepoTest {

    var nabService: NabService = mock(NabService::class.java)
    var forecastDao: ForecastDao = mock(ForecastDao::class.java)
    lateinit var nabRepo: NabRepo

    @Before
    fun setUp() {
        nabRepo = NabRepoImpl(nabService = nabService, forecastDao = forecastDao)
    }

    @Test
    fun loadFromService() {
        `when`(
            forecastDao.loadForeCast(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(Date::class.java)
            )
        ).thenReturn(
            Single.error(Throwable("no data"))
        )

        val forecast = ForeCast(query = "ha noi", id = 1)
        `when`(
            nabService.getDaily(
                ArgumentMatchers.anyString()
            )
        ).thenReturn(
            Observable.just(forecast)
        )

        val testObserver: TestObserver<ForeCast> = nabRepo.getDaily("hanoi").test()
        testObserver.assertNoErrors()
            .assertValue {
                it == forecast
            }
    }

    @Test
    fun loadFromLocal() {
        val forecast = ForeCast(query = "ha noi", id = 1)
        val forecastService = ForeCast(query = "ha noi service", id = 1)
        `when`(
            forecastDao.loadForeCast(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(Date::class.java)
            )
        ).thenReturn(
            Single.just(forecast)
        )

        `when`(
            nabService.getDaily(
                ArgumentMatchers.anyString()
            )
        ).thenReturn(
            Observable.just(forecastService)
        )

        val testObserver: TestObserver<ForeCast> = nabRepo.getDaily("hanoi").test()
        testObserver.assertNoErrors()
            .assertValue {
                it == forecast
            }
    }
}