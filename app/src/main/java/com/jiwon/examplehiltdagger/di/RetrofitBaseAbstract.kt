package com.jiwon.examplehiltdagger.di

import com.google.gson.Gson
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

abstract class RetrofitBaseAbstract<T> {
    protected abstract val BASE_URL : String

    protected abstract val gson : Gson

    abstract fun provideService(
        retrofit : Retrofit
    ) : T

    abstract fun provideRetrofit(
        httpClient : OkHttpClient
    ) : Retrofit

    abstract fun provideGson() : GsonConverterFactory


    @Singleton
    @Provides
    protected fun providesHttpClient(
        httpLoggingInterceptor : HttpLoggingInterceptor
    ) = OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

    abstract fun provideInterceptor() : HttpLoggingInterceptor
}