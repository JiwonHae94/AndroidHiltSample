package com.jiwon.examplehiltdagger.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jiwon.examplehiltdagger.api.UserService
import com.jiwon.examplehiltdagger.data.model.Account
import com.jiwon.examplehiltdagger.repository.UserRepository
import com.nota.fr_gs25.data.parser.AccountParser
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserModule {
    private const val BASE_URL = "https://gsface.nota.ai"
    private val gson = GsonBuilder()
        .registerTypeAdapter(Account::class.java, AccountParser()).create()


    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Singleton
    @Provides
    fun providesOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class AcccountGson

    @AcccountGson
    @Provides
    fun provideGson() = GsonConverterFactory.create(gson)

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient : OkHttpClient,
        @AcccountGson gsonConverter : GsonConverterFactory,
    ) : Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(gsonConverter)
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun provideUserService(
        retrofit : Retrofit
    ) = retrofit.create(UserService::class.java)

    @Singleton
    @Provides
    fun provideUserRepository(
        userService : UserService
    ) = UserRepository(userService)
}