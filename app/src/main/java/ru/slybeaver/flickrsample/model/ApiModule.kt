package ru.slybeaver.flickrsample.model

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by psinetron on 29.05.2018.
 * http://slybeaver.ru
 */
class ApiModule {

    companion object {
        fun getApiInterface(url: String):ApiInterface {
            val httpClient:OkHttpClient = OkHttpClient.Builder()
                    .addNetworkInterceptor(StethoInterceptor())
                    .build()
            val gson: Gson = GsonBuilder().create()

            val builder: Retrofit.Builder = Retrofit.Builder().baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            builder.client(httpClient)

            return builder.build().create(ApiInterface::class.java)
        }
    }

}