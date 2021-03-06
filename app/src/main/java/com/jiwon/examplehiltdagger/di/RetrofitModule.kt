package com.jiwon.examplehiltdagger.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

//    @Singleton
//    @Provides
//    fun provideRetrofit(
//        baseUrl : String,
//        okHttpClient : OkHttpClient,
//        gsonConverter : GsonConverterFactory,
//    ) : Retrofit = Retrofit.Builder()
//        .baseUrl(baseUrl)
//        .addConverterFactory(gsonConverter)
//        .client(okHttpClient)
//        .build()

//
//
//    @Singleton
//    @Provides
//    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor()
//        .apply {
//            level = HttpLoggingInterceptor.Level.BODY
//        }
}