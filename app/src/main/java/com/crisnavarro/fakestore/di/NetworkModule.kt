package com.crisnavarro.fakestore.di

import com.crisnavarro.fakestore.data.network.FakeStoreApi
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
object NetworkModule {

    @Provides
    @Singleton
    fun provideLogging() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ) = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://fakestoreapi.com")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): FakeStoreApi = retrofit.create(FakeStoreApi::class.java)

}