package com.miladofficial.vamtracker.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.miladofficial.vamtracker.data.local.dao.VamDao
import com.miladofficial.vamtracker.data.local.entity.VamEntity
import com.miladofficial.vamtracker.utils.DateConverter

@Database(
    entities = [VamEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(DateConverter::class)
abstract class VamDatabase : RoomDatabase() {

    abstract fun vamDao(): VamDao

    companion object {
        @Volatile
        private var INSTANCE: VamDatabase? = null

        fun getDatabase(context: Context): VamDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    VamDatabase::class.java,
                    "vam_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }
    }
}