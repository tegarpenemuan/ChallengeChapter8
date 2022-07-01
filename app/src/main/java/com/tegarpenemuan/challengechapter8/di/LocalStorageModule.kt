package com.tegarpenemuan.challengechapter8.di

import android.content.Context
import com.tegarpenemuan.challengechapter8.datastore.DatastoreManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalStorageModule {

    @Singleton
    @Provides
    fun provideAuthDataStoreManager(@ApplicationContext context: Context)
            : DatastoreManager {
        return DatastoreManager(context = context)
    }
}