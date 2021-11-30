package com.example.simplerecipes.data.network.client

import com.example.simplerecipes.data.network.RecipeService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit

private const val API_KEY = "64ab844f43124dce87217418b8b9aded"
private const val BASE_URL = "https://api.spoonacular.com"

object RecipesApiClient {
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

    fun createRecipeService(): RecipeService {
        val json = Json {
            prettyPrint = true
            ignoreUnknownKeys = true
        }
        val contentType = "application/json".toMediaType()
        return Retrofit.Builder()
            .client(getHttpClient())
            .baseUrl(BASE_URL)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
            .create(RecipeService::class.java)
    }
}
