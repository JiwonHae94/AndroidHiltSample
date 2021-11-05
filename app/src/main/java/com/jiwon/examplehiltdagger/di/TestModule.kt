package com.jiwon.examplehiltdagger.di

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
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object TestModule { //: RetrofitBase<UserService> {
//    val BASE_URL: String = "https://gsface.nota.ai"
//
//    private val gson = GsonBuilder()
//        .registerTypeAdapter(Account::class.java, AccountParser())
//        .create()
//
//
//    @Qualifier
//    @Retention(AnnotationRetention.BINARY)
//    annotation class TestService
//
//    @Qualifier
//    @Retention(AnnotationRetention.BINARY)
//    annotation class TestRetrofit
//
//    @Qualifier
//    @Retention(AnnotationRetention.BINARY)
//    annotation class TestGson
//
//    @Qualifier
//    @Retention(AnnotationRetention.BINARY)
//    annotation class TestHttpClient
//
//
//    @Singleton
//    @Provides
//    @TestService
//    override fun provideService(
//        @TestRetrofit retrofit: Retrofit,
//    ) = retrofit.create(UserService::class.java)
//
//    @TestRetrofit
//    @Singleton
//    @Provides
//    override fun provideRetrofit(
//        httpClient: OkHttpClient
//    ): Retrofit {
//        return Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create(gson))
//            .client(httpClient)
//            .build()
//    }
//
//    @Singleton
//    @Provides
//    @TestGson
//    override fun provideGson(): GsonConverterFactory {
//        return GsonConverterFactory.create(gson)
//    }
//
//
//    @Singleton
//    @Provides
//    @TestHttpClient
//    override fun providesHttpClient(
//        httpLoggingInterceptor: HttpLoggingInterceptor
//    ): OkHttpClient {
//        return OkHttpClient
//                .Builder()
//                .addInterceptor(httpLoggingInterceptor)
//                .build()
//    }
//
//    @Singleton
//    @Provides
//    override fun provideInterceptor() = HttpLoggingInterceptor()
//        .apply {
//            level = HttpLoggingInterceptor.Level.BODY
//        }
//
//    @Singleton
//    @Provides
//    fun providesOkHttpClient(
//        httpLoggingInterceptor: HttpLoggingInterceptor
//    ): OkHttpClient =
//        OkHttpClient
//            .Builder()
//            .addInterceptor(httpLoggingInterceptor)
//            .build()
//
//    @Singleton
//    @Provides
//    fun provideUserRepository(
//        @TestService userService : UserService
//    ) = UserRepository(userService)
}