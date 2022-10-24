package com.kenchen.capstonenewsapp.di

import com.kenchen.capstonenewsapp.networking.RemoteApi
import com.kenchen.capstonenewsapp.networking.RemoteApiImp
import com.kenchen.capstonenewsapp.prefsstore.PrefsStore
import com.kenchen.capstonenewsapp.prefsstore.PrefsStoreImpl
import com.kenchen.capstonenewsapp.repository.NewsRepository
import com.kenchen.capstonenewsapp.repository.NewsRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindRemoteApi(remoteApiImp: RemoteApiImp): RemoteApi

    @Singleton
    @Binds
    abstract fun bindPrefsStore(prefsStoreImpl: PrefsStoreImpl): PrefsStore

    @Singleton
    @Binds
    abstract fun bindNewsRepository(newsRepositoryImp: NewsRepositoryImp): NewsRepository
}
