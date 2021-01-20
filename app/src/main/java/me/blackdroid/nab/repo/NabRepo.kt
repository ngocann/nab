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

interface NabRepo {
    fun getDaily(query: String): Observable<ForeCast>
}