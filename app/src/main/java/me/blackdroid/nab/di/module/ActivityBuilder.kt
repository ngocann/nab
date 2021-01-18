package me.blackdroid.nab.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import me.blackdroid.nab.ui.main.MainActivity
import me.blackdroid.nab.ui.main.MainActivityModule

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun bindMainActivity(): MainActivity

}