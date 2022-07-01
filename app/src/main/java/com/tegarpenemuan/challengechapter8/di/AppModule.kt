package com.tegarpenemuan.challengchapter6.di

import com.tegarpenemuan.challengechapter8.data.api.tmdb.TMDBAPI
import com.tegarpenemuan.challengechapter8.datastore.DatastoreManager
import com.tegarpenemuan.challengechapter8.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideAuthRepository(
        datastoreManager: DatastoreManager,
        movieApi: TMDBAPI,
    ): Repository {
        return Repository(
            dataStoreManager = datastoreManager,
            movieApi = movieApi,
        )
    }
}