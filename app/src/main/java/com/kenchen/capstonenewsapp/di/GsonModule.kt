package com.kenchen.capstonenewsapp.di

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

// Currently not in use

@InstallIn(SingletonComponent::class)
@Module
object GsonModule {

    @Provides
    fun provideGson(): Gson {
        return Gson()
    }
}
