package me.blackdroid.nab.ext

import io.reactivex.Observable
import me.blackdroid.nab.util.rx.SchedulerProvider

fun <T> Observable<T>.applySchedulers(schedulerProvider: SchedulerProvider): Observable<T> {
    return subscribeOn(schedulerProvider.io())
        .observeOn(schedulerProvider.ui())
}

