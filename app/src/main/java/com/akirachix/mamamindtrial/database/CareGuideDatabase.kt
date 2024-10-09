//package com.akirachix.mamamindtrial.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CareGuideArticle::class], version = 1)
abstract class CareGuideDatabase : RoomDatabase() {
    abstract fun careGuideArticleDao(): CareGuideArticleDao

    companion object {
        @Volatile
        private var INSTANCE: CareGuideDatabase? = null

        fun getDatabase(context: Context): CareGuideDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CareGuideDatabase::class.java,
                    "care_guide_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}


