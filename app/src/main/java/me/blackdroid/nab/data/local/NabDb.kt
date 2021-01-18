package me.blackdroid.nab.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import me.blackdroid.nab.model.ForeCast
import me.blackdroid.nab.util.room.Converters
import me.blackdroid.nab.util.room.ListConverters

@Database(entities = [ForeCast::class], version = 1)
@TypeConverters(value = [Converters::class, ListConverters::class])
abstract class NabDb : RoomDatabase() {
    abstract fun forecastDao() : ForecastDao
}