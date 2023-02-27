package com.randysetiawan.words

import androidx.room.*

@Entity(tableName = "word")
data class WordEntity(
    @PrimaryKey @ColumnInfo(name = "word") val word: String
)
