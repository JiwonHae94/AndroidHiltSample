package com.jiwon.examplehiltdagger.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jiwon.examplehiltdagger.api.UserService
import com.jiwon.examplehiltdagger.data.model.Account
import com.nota.fr_gs25.data.parser.AccountParser
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class RetrofitInherited : RetrofitBaseAbstract<UserService>(){
    override val BASE_URL: String = "https://gsface.nota.ai"

    override val gson: Gson = GsonBuilder()
        .registerTypeAdapter(Account::class.java, AccountParser())
        .create()

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class InheritService

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class InheritRetrofit

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class InheritGson

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class InheritLogger

    @Singleton
    @Provides
    @InheritService
    override fun provideService(
        @InheritRetrofit retrofit: Retrofit
    ): UserService {
        return retrofit.create(UserService::class.java)
    }


    @Singleton
    @Provides
    @InheritRetrofit
    override fun provideRetrofit(
        @InheritLogger httpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(httpClient)
            .build()
    }

    @Singleton
    @Provides
    @InheritGson
    override fun provideGson(): GsonConverterFactory {
        return GsonConverterFactory.create(gson)
    }


    @Singleton
    @Provides
    @InheritLogger
    override fun provideInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
}