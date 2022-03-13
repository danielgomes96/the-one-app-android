package com.devlabs.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devlabs.domain.entity.Character
import com.devlabs.domain.entity.Movie
import com.devlabs.domain.entity.ResultWrapper
import com.devlabs.domain.usecase.GetCharactersUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CharactersListViewModel(
    private val getCharactersUseCase: GetCharactersUseCase
): ViewModel() {

    var currentPage = 0

    private val _charactersListLiveData = MutableLiveData<ResultWrapper<List<Character>>>(ResultWrapper.InitialState())
    val charactersListLiveData: LiveData<ResultWrapper<List<Character>>>
        get() = _charactersListLiveData

    fun requestCharacters() = CoroutineScope(Dispatchers.IO).launch {
        currentPage = currentPage.inc()
        getCharactersUseCase.execute(currentPage).collect {
            _charactersListLiveData.postValue(it)
        }
    }

}