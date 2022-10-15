package com.kenchen.capstonenewsapp.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kenchen.capstonenewsapp.model.Article

@Dao
interface ArticleDao {

    @Query("SELECT * FROM article")
    suspend fun getArticles():List<Article>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addArticles(articles:List<Article>)

    @Query("DELETE FROM article")
    suspend fun clearArticles()

    @Query("SELECT * FROM article WHERE title LIKE :search")
    suspend fun searchArticles(search:String): List<Article>
}
