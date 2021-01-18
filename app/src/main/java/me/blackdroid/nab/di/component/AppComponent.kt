/*
 * AppComponent.java
 *
 * Created by quan.pham
 * Copyright (c) 2018 Home Credit Vietnam. All rights reserved.
 *
 * Last modified 6/12/18 10:42 AM
 */
package me.blackdroid.nab.di.component

import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjection
import me.blackdroid.nab.NabApp
import me.blackdroid.nab.di.module.ActivityBuilder
import me.blackdroid.nab.di.module.AppModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        ActivityBuilder::class
    ]
)
interface AppComponent : AndroidInjector<NabApp> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<NabApp>()
}