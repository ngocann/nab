package me.blackdroid.nab.util.room

import androidx.room.TypeConverter
import com.google.gson.Gson
import java.util.*

object GsonConverters {
    @TypeConverter
    fun fromObjectToString(model: Any?): String? {
        return Gson().toJson(model)
    }

    @TypeConverter
    fun fromStringToObject(json: String?): Any? {
        return Gson().fromJson(json, Any::class.java)
    }
}