package br.com.fiap.ecolens.clients

import br.com.fiap.ecolens.BuildConfig
import br.com.fiap.ecolens.services.OpenAQService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient

object ApiClient {

    private const val BASE_URL = "https://api.openaq.org/"
    private val client = OkHttpClient.Builder()
        .addInterceptor(Interceptor { chain ->

            val request = chain.request().newBuilder()
                .addHeader("X-API-Key", BuildConfig.OPENAQ_API_KEY)
                .build()

            chain.proceed(request)
        })
        .build()

    val service: OpenAQService by lazy {

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(OpenAQService::class.java)
    }
}