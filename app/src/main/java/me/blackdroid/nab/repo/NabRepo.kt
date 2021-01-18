package me.blackdroid.nab.repo

import android.util.Log
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.Observer
import io.reactivex.functions.Function
import me.blackdroid.nab.data.local.ForecastDao
import me.blackdroid.nab.data.remote.NabService
import me.blackdroid.nab.model.ForeCast
import java.util.*
import javax.inject.Inject

class NabRepo @Inject constructor(
    private val nabService: NabService,
    private val forecastDao: ForecastDao
) {

    fun getDaily(query: String): Observable<ForeCast> {
        return forecastDao.loadForeCast(
            query = query,
            date = Calendar.getInstance().time
        )
            .toObservable()
            .onErrorResumeNext(Function {
                return@Function nabService.getDaily(query = query)
                    .doOnNext {
                        it.query = query
                        val currentDate = Calendar.getInstance()
                        currentDate.add(Calendar.MINUTE, 15)
                        it.last_update = currentDate.time
                        forecastDao.save(foreCast = it)
                    }
            })
    }

}