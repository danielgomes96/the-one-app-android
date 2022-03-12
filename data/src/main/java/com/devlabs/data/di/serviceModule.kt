package com.devlabs.data.di

import com.devlabs.data.service.TheOneAPI
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.BuildConfig
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val BASE_API_URL = "https://the-one-api.dev/v2/"

val serviceModule = module {
    factory {
        getTheOneAPIService(
            get<Retrofit>()
        )
    }

    single {
        createBoredAPIService(
            get()
        )
    }

    factory {
        createOkHttpClient()
    }
}

private fun getTheOneAPIService(retrofit: Retrofit): TheOneAPI =
    retrofit.create(TheOneAPI::class.java)

private fun createBoredAPIService(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
    .baseUrl(BASE_API_URL)
    .client(okHttpClient)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

private fun createOkHttpClient(): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    return OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor)
        .apply {
            addInterceptor(
                Interceptor { chain ->
                    val builder = chain.request().newBuilder()
                    builder.header("Authorization", "Bearer ${com.devlabs.data.BuildConfig.TOKEN_API}")
                    return@Interceptor chain.proceed(builder.build())
                }
            )
        }.build()
}
