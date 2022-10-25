package com.kenchen.capstonenewsapp.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.room.Room
import com.kenchen.capstonenewsapp.database.NewsDatabase
import com.kenchen.capstonenewsapp.database.dao.ArticleDao
import com.kenchen.capstonenewsapp.database.dao.SourceDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Room database module for dependency injection
 * */

@InstallIn(SingletonComponent::class)
@Module
object LocalDatabaseModule {
    private const val DATABASE_NAME = "News"

    @Singleton
    @Provides
    fun provideArticleDao(newsDatabase: NewsDatabase):ArticleDao {
        return newsDatabase.articleDao()
    }

    @Singleton
    @Provides
    fun provideSourceDao(newsDatabase: NewsDatabase):SourceDao {
        return newsDatabase.sourceDao()
    }

    @Singleton
    @Provides
    fun provideNewsDatabase(@ApplicationContext context: Context):NewsDatabase {
        return Room.databaseBuilder(
            context,
            NewsDatabase::class.java,
            DATABASE_NAME
        )
            .build()
    }

    @Singleton
    @Provides
    fun providePrefsStore(@ApplicationContext context: Context) : DataStore<Preferences> {
        return PreferenceDataStoreFactory.create {
                context.preferencesDataStoreFile(name = "settings")
            }
    }

}
