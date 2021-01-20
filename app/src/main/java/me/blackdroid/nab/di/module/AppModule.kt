package me.blackdroid.nab.di.module

import androidx.room.Room
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import me.blackdroid.nab.NabApp
import me.blackdroid.nab.data.local.ForecastDao
import me.blackdroid.nab.data.local.NabDb
import me.blackdroid.nab.data.remote.NabService
import me.blackdroid.nab.repo.NabRepo
import me.blackdroid.nab.repo.NabRepoImpl
import me.blackdroid.nab.util.rx.AppSchedulerProvider
import me.blackdroid.nab.util.rx.SchedulerProvider
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class AppModule {

    private val BASE_URL = "https://api.openweathermap.org"
    private val NAB_DB = "nab.db"

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        val interceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val client =
            OkHttpClient.Builder().addInterceptor(interceptor).build()
        return Retrofit.Builder().baseUrl(BASE_URL)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    }

    @Singleton
    @Provides
    fun provideNabService(retrofit: Retrofit): NabService {
        return retrofit.create(NabService::class.java)
    }

    @Singleton
    @Provides
    fun provideDb(app: NabApp): NabDb {
        val builder =  Room.databaseBuilder(app, NabDb::class.java, NAB_DB)
        val factory = SupportFactory(SQLiteDatabase.getBytes("PassPhrase".toCharArray()))
        builder.openHelperFactory(factory)
        return builder.build()
    }


    @Singleton
    @Provides
    fun provideForecastDao(db: NabDb): ForecastDao {
        return db.forecastDao()
    }

    @Singleton
    @Provides
    fun provideSchedulerProvider(): SchedulerProvider {
        return AppSchedulerProvider()
    }

    @Singleton
    @Provides
    fun provideNabRepo(nabService: NabService,
                       forecastDao: ForecastDao): NabRepo {
        return NabRepoImpl(nabService, forecastDao)
    }

}