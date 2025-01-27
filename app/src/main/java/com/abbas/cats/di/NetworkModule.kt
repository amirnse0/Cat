package com.abbas.cats.di

import com.abbas.cats.data.remote.CatsService
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {
    private val TIME_OUT = 30L
    private val API_KEY = "live_5s4OIlSoyZAdD9JtqijTLuaYV0t2JqSwWR7QjA0xB6rJw4wq3dUpQzHiKR534j2c "
    private val BASE_URL = "https://api.thecatapi.com/v1/"

    @Singleton
    @Provides
    fun provideOkHttpInterceptor(): Interceptor = Interceptor { chain ->
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        loggingInterceptor.intercept(chain)

        val request = chain.request()
        val newRequest = request.newBuilder()
            .addHeader("x-api-key", API_KEY)
            .method(request.method, request.body)
            .build()

        chain.proceed(newRequest)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        interceptor: Interceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
        .readTimeout(TIME_OUT, TimeUnit.SECONDS)
        .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
        .apply {
            addInterceptor(interceptor)
        }
        .build()

    @Singleton
    @Provides
    fun provideGson(): Gson = Gson()

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gson: Gson
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    @Singleton
    @Provides
    fun provideCatsService(
        retrofit: Retrofit
    ): CatsService = retrofit.create(CatsService::class.java)
}