package me.blackdroid.nab.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.functions.Consumer
import me.blackdroid.nab.ext.applySchedulers
import me.blackdroid.nab.model.ForeCast
import me.blackdroid.nab.repo.NabRepo
import me.blackdroid.nab.util.rx.SchedulerProvider
import me.blackdroid.nab.util.rx.ThreadUtil
import java.lang.Exception
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val nabRepo: NabRepo,
    private val schedulerProvider: SchedulerProvider
) : ViewModel() {

    val foreCast = MutableLiveData<ForeCast>()
    val query = MutableLiveData<String>("")
    val showLoading = MutableLiveData<Boolean>()
    val message = MutableLiveData<String>("")

    fun loadDaily() {
        query.value?.let { query ->
            Observable.just(query.length)
                .doOnNext {
                    if (it < 3) {
                        throw Exception("Please enter at least 3 digit")
                    }
                }
                .flatMap { nabRepo.getDaily(query = query) }
                .compose(ThreadUtil.applyObservableSchedulers(schedulerProvider))
                .doOnSubscribe {
                    message.value = ""
                    showLoading.value = true
                }
                .doAfterTerminate { showLoading.value = false }
                .subscribe({ forecast ->
                    foreCast.value = forecast
                }, {
                    message.value = it.message
                    it.printStackTrace()
                })
        }
    }




}