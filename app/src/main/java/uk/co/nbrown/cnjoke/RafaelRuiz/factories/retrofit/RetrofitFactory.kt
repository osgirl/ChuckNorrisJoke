package uk.co.nbrown.cnjoke.RafaelRuiz.factories.retrofit

import android.arch.lifecycle.BuildConfig
import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import uk.co.nbrown.cnjoke.RafaelRuiz.screens.ChuckNorrisJokeApplication
import java.util.concurrent.TimeUnit

/**
 * Created by rafaelruizmunoz on 08/11/2017.
 */

object RetrofitFactory {

    fun createRetrofit(baseUrl: String): Retrofit {
        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(ChuckNorrisJokeApplication.application!!.getGson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
                .client(createOkHttpClient())
                .build()
    }

    private fun createOkHttpClient(): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(createLoggingInterceptor())

        httpClient.connectTimeout(60, TimeUnit.SECONDS)
        httpClient.readTimeout(60, TimeUnit.SECONDS)

        return httpClient.build()
    }

    private fun createLoggingInterceptor(): HttpLoggingInterceptor {
        val logger = HttpLoggingInterceptor { message -> Log.i("HTTP", message) }
        logger.level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        return logger
    }

}