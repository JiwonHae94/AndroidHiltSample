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
import javax.inject.Inject
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserModule {
    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class AcccountGson

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class AccountRetrofit

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class AccountService

    private const val BASE_URL = "https://gsface.nota.ai"
    private val gson = GsonBuilder()
        .registerTypeAdapter(Account::class.java, AccountParser()).create()

    @Singleton
    @Provides
    fun providesOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()


    @AcccountGson
    @Provides
    fun provideGson() = GsonConverterFactory.create(gson)

    @Provides
    fun getService() = UserService::class.java

    @AccountRetrofit
    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient : OkHttpClient,
        @AcccountGson gsonConverter : GsonConverterFactory,
    ) = RetrofitModule.provideRetrofit(
        BASE_URL,
        okHttpClient,
        gsonConverter
    )

    @Singleton
    @Provides
    fun provideService(
        @AccountRetrofit retrofit : Retrofit
    ) = retrofit.create(UserService::class.java)

    @Singleton
    @Provides
    fun provideUserRepository(
        userService : UserService
    ) = UserRepository(userService)
}