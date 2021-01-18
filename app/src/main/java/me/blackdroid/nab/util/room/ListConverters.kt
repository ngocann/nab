package me.blackdroid.nab.util.room

import androidx.room.TypeConverter
import com.google.gson.Gson

import com.google.gson.reflect.TypeToken
import me.blackdroid.nab.model.Daily
import java.lang.reflect.Type


object ListConverters {
    @TypeConverter
    @JvmStatic
    fun fromString(countryLang: List<Daily>?): String? {
        if (countryLang == null) {
            return null
        }
        val gson = Gson()
        val type =
            object : TypeToken<List<Daily>?>() {}.type
        return gson.toJson(countryLang, type)
    }

    @TypeConverter
    @JvmStatic
    fun fromArrayList(countryLangString: String?): List<Daily>? {
        if (countryLangString == null) {
            return null
        }
        val gson = Gson()
        val type =
            object : TypeToken<List<Daily>?>() {}.type
        return gson.fromJson(countryLangString, type)
    }
}