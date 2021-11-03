package com.configuration

object Hilt{
    private const val version = "2.38.1"
    const val classPath = "com.google.dagger:hilt-android-gradle-plugin:$version"

    const val kapt = "com.google.dagger:hilt-compiler:$version"
    const val api = "com.google.dagger:hilt-android:$version"
}

object Retrofit{
    private const val version = "2.6.0"
    const val api = "com.squareup.retrofit2:retrofit:$version"
    const val gsonConverter = "com.squareup.retrofit2:converter-gson:$version"

    private const val logVersion = "3.12.0"
    const val logging = "com.squareup.okhttp3:logging-interceptor:$logVersion"

    private const val jsonVersion = "2.8.6"
    const val json = "com.google.code.gson:gson:$jsonVersion"
}


object Concurrent{
    val future = "androidx.concurrent:concurrent-futures:1.1.0"
    val concurrentFuture = "androidx.concurrent:concurrent-futures-ktx:1.1.0"

    private const val coroutineVersion = "1.5.2"
    const val coroutine = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutineVersion"
    const val coroutineCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutineVersion"

    const val lifecycleActivity = "androidx.activity:activity-ktx:1.1.0"
    const val lifecycleFragment = "androidx.fragment:fragment-ktx:1.3.5"
}