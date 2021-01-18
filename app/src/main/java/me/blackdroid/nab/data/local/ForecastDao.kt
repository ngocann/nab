package me.blackdroid.nab.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import me.blackdroid.nab.model.ForeCast
import java.util.*

@Dao
interface ForecastDao {

    @Query("SELECT * FROM forecast where `query` LIKE '%' || :query || '%' AND last_update >= :date")
    fun loadForeCast(query: String, date: Date?): Single<ForeCast>

    @Insert(onConflict = REPLACE)
    fun save(foreCast: ForeCast)

}