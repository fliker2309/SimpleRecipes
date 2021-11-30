package com.example.simplerecipes.data.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

private const val API_KEY = "64ab844f43124dce87217418b8b9aded"
private const val BASE_URL = "https://api.spoonacular.com"

object RetrofitConfig {

    private fun getHttpClient(): OkHttpClient {
        val requestInterceptor = Interceptor { chain ->
            val url = chain.request()
                .url
                .newBuilder()
                .addQueryParameter("apiKey", API_KEY)
                .build()

            val request = chain.request()
                .newBuilder()
                .url(url)
                .build()

            return@Interceptor chain.proceed(request)
        }

        val httpClient = OkHttpClient.Builder()
            .addInterceptor(requestInterceptor)

        return httpClient.build()
    }

    private val json = Json {
        prettyPrint = true
        ignoreUnknownKeys = true
    }

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val contentType = "application/json".toMediaType()
/*
    private val httpClient = OkHttpClient.Builder()
        .addInterceptor(getHttpClient)
        .addInterceptor(HttpLoggingInterceptor())
        .addNetworkInterceptor(loggingInterceptor)
        .build()*/

    private val retrofitBuilder = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(getHttpClient())
        .addConverterFactory(json.asConverterFactory(contentType))
        .build()

    val recipeService: RecipeService = retrofitBuilder.create(RecipeService::class.java)
}
