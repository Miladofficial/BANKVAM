package com.miladofficial.vamtracker.di

import android.content.Context
import androidx.room.Room
import com.miladofficial.vamtracker.data.local.dao.VamDao
import com.miladofficial.vamtracker.data.local.database.VamDatabase
import com.miladofficial.vamtracker.data.repository.VamRepository
import com.miladofficial.vamtracker.data.repository.VamRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideVamDatabase(@ApplicationContext context: Context): VamDatabase {
        return Room.databaseBuilder(
            context,
            VamDatabase::class.java,
            "vam_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideVamDao(database: VamDatabase): VamDao {
        return database.vamDao()
    }

    @Provides
    @Singleton
    fun provideVamRepository(repositoryImpl: VamRepositoryImpl): VamRepository {
        return repositoryImpl
    }
}