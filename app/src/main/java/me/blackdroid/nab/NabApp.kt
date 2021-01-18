package me.blackdroid.nab

import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import me.blackdroid.nab.di.component.DaggerAppComponent

class NabApp : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().create(this)
    }
}