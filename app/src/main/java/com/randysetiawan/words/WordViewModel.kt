package com.randysetiawan.words

import androidx.lifecycle.*
import kotlinx.coroutines.launch

class WordViewModel(private val wordRepository: WordRepository): ViewModel() {
    val allWords: LiveData<List<WordEntity>> = wordRepository.allWords.asLiveData()
    fun insert(wordEntity: WordEntity) = viewModelScope.launch {
        wordRepository.insert(wordEntity)
    }
}

class WordViewModelFactory(private val wordRepository: WordRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(WordViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WordViewModel(wordRepository) as T
        }
        throw java.lang.IllegalArgumentException("Unknown ViewModel class")
    }
}