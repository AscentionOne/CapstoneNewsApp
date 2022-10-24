package com.kenchen.capstonenewsapp.di

import android.content.Context
import androidx.room.Room
import com.kenchen.capstonenewsapp.database.NewsDatabase
import com.kenchen.capstonenewsapp.database.dao.ArticleDao
import com.kenchen.capstonenewsapp.database.dao.SourceDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

/**
 * Room database module for dependency injection
 * */

@InstallIn(SingletonComponent::class)
@Module
object LocalDatabaseModule {
    private const val DATABASE_NAME = "News"

    @Provides
    fun provideArticleDao(newsDatabase: NewsDatabase):ArticleDao {
        return newsDatabase.articleDao()
    }

    @Provides
    fun provideSourceDao(newsDatabase: NewsDatabase):SourceDao {
        return newsDatabase.sourceDao()
    }

    @Provides
    fun provideNewsDatabase(@ApplicationContext context: Context):NewsDatabase {
        return Room.databaseBuilder(
            context,
            NewsDatabase::class.java,
            DATABASE_NAME
        )
            .build()
    }
}
