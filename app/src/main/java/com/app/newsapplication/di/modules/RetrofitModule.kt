package com.app.newsapplication.di.modules

import com.app.newsapplication.api.NewsAPI
import com.app.newsapplication.utils.Urls.BASE_URL
import com.google.gson.Gson
import com.google.gson.GsonBuilder
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
object RetrofitModule {

    @Singleton
    @Provides
    fun providesClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient().newBuilder()
            .addInterceptor(logging)
            .build()
    }

    @Singleton
    @Provides
    fun provideGson(): Gson = GsonBuilder().setLenient().create()

    @Singleton
    @Provides
    fun provideGsonConverterFactory(gson: Gson): GsonConverterFactory =
        GsonConverterFactory.create(gson)

    @Singleton
    @Provides
    fun provideRetrofitInstance(client: OkHttpClient, factory: GsonConverterFactory): Retrofit =
        Retrofit.Builder().client(client).addConverterFactory(factory).baseUrl(BASE_URL).build()

    @Singleton
    @Provides
    fun provideRetrofitClient(retrofit: Retrofit): NewsAPI = retrofit.create(NewsAPI::class.java)

}
