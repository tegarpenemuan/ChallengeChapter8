package com.tegarpenemuan.challengechapter8.di

import com.tegarpenemuan.challengechapter8.data.api.tmdb.TMDBAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    //baseURL
    @Singleton
    @Provides
    fun provideBaseUrl() = "https://api.themoviedb.org/3/"

    //logging
    @Singleton
    @Provides
    fun provideHttpLogging(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        return httpLoggingInterceptor.apply {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }
    }

    //Client
    @Singleton
    @Provides
    fun provideOkhttpClient(logging: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    //Retrofit
    @Singleton
    @Provides
    fun provideRetrofit(baseUrl: String, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    //Instance
    @Singleton
    @Provides
    fun provideAPI(retrofit: Retrofit): TMDBAPI {
        return retrofit.create(TMDBAPI::class.java)
    }
}