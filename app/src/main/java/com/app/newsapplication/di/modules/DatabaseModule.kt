package com.app.newsapplication.di.modules

import android.content.Context
import androidx.room.Room
import com.app.newsapplication.db.ArticleDatabase
import com.app.newsapplication.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context.applicationContext,
            ArticleDatabase::class.java,
            Constants.DATABASE_NAME
        ).build()

    @Singleton
    @Provides
    fun provideArticleDao(db: ArticleDatabase) = db.getArticleDao()
}