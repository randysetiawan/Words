package com.randysetiawan.words

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class WordsApplication: Application() {
    val wordsScope = CoroutineScope(SupervisorJob())
    val wordsDatabase by lazy { WordsRoomDatabase.getDatabase(this, wordsScope) }
    val wordRepository by lazy { WordRepository(wordsDatabase.wordDao()) }
}