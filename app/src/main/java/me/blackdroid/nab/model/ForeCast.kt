package me.blackdroid.nab.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import me.blackdroid.nab.util.room.Converters
import me.blackdroid.nab.util.room.GsonConverters
import me.blackdroid.nab.util.room.ListConverters
import java.util.*

@Entity
data class ForeCast(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val cnt: Int? = null,
    val cod: String? = null,
    val list: List<Daily>? = null,
    val message: Double? = null,
    var last_update: Date? = null,
    var query: String
)

data class City(
    val coord: Coord?,
    val country: String?,
    val id: Int?,
    val name: String?,
    val population: Int?,
    val timezone: Int?
)

data class Coord(
    val lat: Double?,
    val lon: Double?
)

data class Daily(
    val clouds: Int?,
    val deg: Int?,
    val dt: Int?,
    val feels_like: FeelsLike?,
    val humidity: Int?,
    val pressure: Int?,
    val rain: Double?,
    val speed: Double?,
    val sunrise: Int?,
    val sunset: Int?,
    val temp: Temp?,
    val weather: List<Weather?>?
)

data class FeelsLike(
    val day: Double?,
    val eve: Double?,
    val morn: Double?,
    val night: Double?
)

data class Temp(
    val day: Double?,
    val eve: Double?,
    val max: Double?,
    val min: Double?,
    val morn: Double?,
    val night: Double?
)

data class Weather(
    val description: String?,
    val icon: String?,
    val id: Int?,
    val main: String?
)
