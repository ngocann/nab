package me.blackdroid.nab.data.remote

import io.reactivex.Observable
import io.reactivex.Single
import me.blackdroid.nab.model.ForeCast
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NabService {
    @GET("data/2.5/forecast/daily?cnt=7&appid=60c6fbeb4b93ac653c492ba806fc346d")
    fun getDaily(@Query("q") query: String): Observable<ForeCast>
}