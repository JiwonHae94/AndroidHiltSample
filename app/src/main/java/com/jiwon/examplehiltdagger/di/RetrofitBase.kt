package com.jiwon.examplehiltdagger.di

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface RetrofitBase<T> {
    fun provideService(
        retrofit : Retrofit
    ) : T

    fun provideRetrofit(
        httpClient : OkHttpClient
    ) : Retrofit

    fun provideGson() : GsonConverterFactory

    fun providesHttpClient(interceptor : HttpLoggingInterceptor) : OkHttpClient

    fun provideInterceptor() : HttpLoggingInterceptor
}