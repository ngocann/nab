package me.blackdroid.nab.util

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import me.blackdroid.nab.util.rx.SchedulerProvider

class ImmediateSchedulerProvider : SchedulerProvider {
    override fun computation(): Scheduler {
        return Schedulers.trampoline()
    }

    override fun io(): Scheduler {
        return Schedulers.trampoline()
    }

    override fun ui(): Scheduler {
        return Schedulers.trampoline()
    }
}