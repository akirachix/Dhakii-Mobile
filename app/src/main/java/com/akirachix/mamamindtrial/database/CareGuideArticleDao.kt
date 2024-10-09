//package com.akirachix.mamamindtrial.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CareGuideArticleDao {
    @Query("SELECT * FROM care_guide_articles WHERE category = :category")
    fun getArticlesByCategory(category: String): Flow<List<CareGuideArticle>>

    @Insert
    suspend fun insertAll(vararg articles: CareGuideArticle)
}

