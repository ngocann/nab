package me.blackdroid.nab.ui.main

import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import me.blackdroid.nab.di.ViewModelProviderFactory

@Module
class MainActivityModule {
    @Provides
    fun provideSplashViewModel(mainViewModel: MainViewModel): ViewModelProvider.Factory {
        return ViewModelProviderFactory(mainViewModel)
    }

}