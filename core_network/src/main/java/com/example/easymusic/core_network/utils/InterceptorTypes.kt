package com.example.easymusic.core_network.utils

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import com.example.core_network.BuildConfig
import com.example.easymusic.core.StringConstants
import okhttp3.Interceptor

private fun collector(context: Context) = ChuckerCollector(
    context = context,
    showNotification = true,
    retentionPeriod = RetentionManager.Period.ONE_HOUR
)

@Suppress("MagicNumber")
fun getChuckerInterceptor(context: Context) = ChuckerInterceptor.Builder(context)
    .collector(collector(context))
    .maxContentLength(250_000L)
    .redactHeaders(emptySet())
    .alwaysReadResponseBody(false)
    .build()


enum class InterceptorType {
    APPLICATION,
    NETWORK,
    ;

    interface Provider {
        val value: InterceptorType
    }
}

fun Interceptor.activeForType(
    activeType: InterceptorType,
    typeProvider: InterceptorType.Provider,
) = Interceptor { chain ->
    if (activeType == typeProvider.value) {
        intercept(chain)
    } else {
        chain.proceed(chain.request())
    }
}

fun createBaseInterceptor(): Interceptor {
    return Interceptor { chain ->
        val newBuilder = chain.request().newBuilder()
        val X_RapidAPI_Key = BuildConfig.X_RapidAPI_Key
        val ToX_RapidAPI_Hostken = BuildConfig.X_RapidAPI_Host
        newBuilder.addHeader(StringConstants.keyHeaderTitle, X_RapidAPI_Key)
        newBuilder.addHeader(StringConstants.hostHeaderTitle, ToX_RapidAPI_Hostken)
        return@Interceptor chain.proceed(newBuilder.build())
    }
}