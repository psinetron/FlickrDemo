package ru.slybeaver.flickrsample.model

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import ru.slybeaver.flickrsample.dto.SearchListDTO

/**
 * Created by psinetron on 29.05.2018.
 * http://slybeaver.ru
 */
interface ApiInterface {

    @GET(".")
    fun search(@Query("method") method:String, @Query("api_key") key:String, @Query("format") format:String, @Query("nojsoncallback") noCallback:String, @Query("page") page:Int, @Query("per_page") pager:Int, @Query("text") text:String): Single<SearchListDTO>


}