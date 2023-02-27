package com.randysetiawan.words
import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.*

class WordRepository(private val wordDao: WordDao) {
    val allWords: Flow<List<WordEntity>> = wordDao.getAlphabetizedWords()
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(wordEntity: WordEntity) {
        wordDao.insert(wordEntity)
    }
}