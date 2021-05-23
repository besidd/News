package com.app.newsapplication.di.modules

import com.app.newsapplication.api.NewsAPI
import com.app.newsapplication.db.ArticleDatabase
import com.app.newsapplication.repository.NewsRepository
import com.app.newsapplication.repository.NewsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideRepository(db: ArticleDatabase, api: NewsAPI): NewsRepository =
        NewsRepositoryImpl(db, api)
}