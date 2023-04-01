package com.example.easymusic.di

import android.content.Context
import com.example.easymusic.BuildConfig
import com.example.easymusic.core_network.source.ApiService
import com.example.easymusic.core_network.utils.*
import com.example.easymusic.core_network.utils.retrofit.ResultAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private val interceptorTypeSelector = InterceptorTypeSelector()
    private val base_url = BuildConfig.base_url

    @Provides
    @Singleton
    fun provideOkHttpClient(
        @ApplicationContext context: Context
    ): OkHttpClient = OkHttpClient
        .Builder()
        .addInterceptor(
            getChuckerInterceptor(context).activeForType(
                InterceptorType.APPLICATION,
                interceptorTypeSelector
            )
        )
        .addNetworkInterceptor(
            getChuckerInterceptor(context).activeForType(
                InterceptorType.NETWORK,
                interceptorTypeSelector
            )
        )
        .addInterceptor(createBaseInterceptor())
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(mOkHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(base_url)
            .client(mOkHttpClient)
            .addCallAdapterFactory(ResultAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideAuthService(client: Retrofit): ApiService = client.create(ApiService::class.java)
}