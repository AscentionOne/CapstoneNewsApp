package com.kenchen.capstonenewsapp.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kenchen.capstonenewsapp.model.Source

@Dao
interface SourceDao {

    @Query("SELECT * FROM source")
    suspend fun getSources():List<Source>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addSources(sources:List<Source>)
}
