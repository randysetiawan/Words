package com.randysetiawan.words

import androidx.room.*
import kotlinx.coroutines.*

@Dao
interface WordDao {

    @Query("SELECT * FROM word ORDER BY word ASC")
    fun getAlphabetizedWords(): kotlinx.coroutines.flow.Flow<List<WordEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(wordEntity: WordEntity)

    @Query("DELETE FROM word")
    fun deleteAll()
}