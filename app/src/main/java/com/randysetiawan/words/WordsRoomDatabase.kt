package com.randysetiawan.words

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(WordEntity::class), version = 1, exportSchema = false)
public abstract class WordsRoomDatabase: RoomDatabase() {
    companion object {
        private var wordsRoomDatabase: WordsRoomDatabase? = null
        fun getDatabase(context: Context, coroutineScope: CoroutineScope): WordsRoomDatabase {
            return wordsRoomDatabase ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WordsRoomDatabase::class.java,
                    "words_database"
                ).allowMainThreadQueries().addCallback(WordsRoomDatabaseCallback(coroutineScope)).build()
                wordsRoomDatabase = instance
                instance
            }
        }
    }
    private class WordsRoomDatabaseCallback(private val coroutineScope: CoroutineScope): RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            wordsRoomDatabase?.let { database ->
                coroutineScope.launch {
                    populateDatabase(database.wordDao())
                }
            }
        }

        suspend fun populateDatabase(wordDao: WordDao) {
            wordDao.deleteAll()
            var wordEntity = WordEntity("Hello")
            wordDao.insert(wordEntity)
            wordEntity = WordEntity("World!")
            wordDao.insert(wordEntity)
        }
    }
    abstract fun wordDao(): WordDao
}