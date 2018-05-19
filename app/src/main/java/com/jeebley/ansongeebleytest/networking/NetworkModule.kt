package com.jeebley.ansongeebleytest.networking

import com.jeebley.ansongeebleytest.BuildConfig

import java.io.File
import java.io.IOException

import javax.inject.Singleton

import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

@Module
class NetworkModule(internal var cacheFile: File) {

    @Provides
    @Singleton
    internal fun provideCall(): Retrofit {
        var cache: Cache? = null
        try {
            cache = Cache(cacheFile, (10 * 1024 * 1024).toLong())
        } catch (e: Exception) {
            e.printStackTrace()
        }

        val okHttpClient = OkHttpClient.Builder()
                .addInterceptor { chain ->
                    val original = chain.request()

                    // Customize the request
                    val request = original.newBuilder()
                            .header("Content-Type", "application/json")
                            .removeHeader("Pragma")
                            .header("Cache-Control", String.format("max-age=%d", BuildConfig.CACHETIME))
                            .build()

                    val response = chain.proceed(request)
                    response.cacheResponse()
                    // Customize or return the response
                    response
                }
                .cache(cache)

                .build()


        return Retrofit.Builder()
                .baseUrl(BuildConfig.BASEURL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())

                .build()
    }

    @Provides
    @Singleton
    fun providesNetworkService(
            retrofit: Retrofit): NetworkService {
        return retrofit.create(NetworkService::class.java!!)
    }

    @Provides
    @Singleton
    fun providesService(
            networkService: NetworkService): Service {
        return Service(networkService)
    }

}