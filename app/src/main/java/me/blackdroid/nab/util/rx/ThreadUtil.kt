package me.blackdroid.nab.util.rx

import io.reactivex.*

object ThreadUtil {
    fun <T> applySchedulers(schedulerProvider: SchedulerProvider): SingleTransformer<T, T> {
        return SingleTransformer { observable: Single<T> ->
            observable
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
        }
    }

    fun <T> applyObservableSchedulers(schedulerProvider: SchedulerProvider): ObservableTransformer<T, T> {
        return ObservableTransformer { observable: Observable<T> ->
            observable
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
        }
    }

    fun <T> applyFlowableSchedulers(schedulerProvider: SchedulerProvider): FlowableTransformer<T, T> {
        return FlowableTransformer { flowable: Flowable<T> ->
            flowable
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
        }
    }
}