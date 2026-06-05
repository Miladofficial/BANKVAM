/**
 * تنظیمات Hilt
 */

package com.miladofficial.vamtracker.di

import android.content.Context
import androidx.room.Room
import com.miladofficial.vamtracker.data.local.database.VamDatabase
import com.miladofficial.vamtracker.data.repository.VamRepository
import com.miladofficial.vamtracker.data.repository.VamRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * ماژول Hilt برای تزریق وابستگی‌ها
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /**
     * تزریق دیتابیس
     */
    @Singleton
    @Provides
    fun provideVamDatabase(
        @ApplicationContext context: Context
    ): VamDatabase {
        return Room.databaseBuilder(
            context,
            VamDatabase::class.java,
            "vam_database"
        ).build()
    }

    /**
     * تزریق DAO
     */
    @Singleton
    @Provides
    fun provideVamDao(database: VamDatabase) = database.vamDao()

    /**
     * تزریق Repository
     */
    @Singleton
    @Provides
    fun provideVamRepository(impl: VamRepositoryImpl): VamRepository = impl
}
