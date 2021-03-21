package com.example.guava.di

import com.example.guava.oauth.OAuthService
import com.facebook.flipper.plugins.network.FlipperOkhttpInterceptor
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {
    @Provides
    @Singleton
    fun provideMoshi(adapterFactorySet: Set<@JvmSuppressWildcards JsonAdapter.Factory>): Moshi {
        val builder = Moshi.Builder()

        adapterFactorySet.forEach {
            builder.add(it)
        }

        return builder.build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .readTimeout(100, TimeUnit.SECONDS)
            .connectTimeout(100, TimeUnit.SECONDS)
            .addNetworkInterceptor(FlipperOkhttpInterceptor(NetworkFlipperPlugin()))
            .build()


    @Provides
    @Singleton
    fun provideStravaAuthService(okHttpClient: OkHttpClient): OAuthService {
        return Retrofit.Builder()
            .baseUrl(OAuthService.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(OAuthService::class.java)
    }
}