package com.devlabs.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devlabs.domain.entity.Character
import com.devlabs.domain.entity.Quote
import com.devlabs.domain.entity.ResultWrapper
import com.devlabs.domain.usecase.GetCharacterQuotesUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CharacterDetailsViewModel(
    private val getCharacterQuotesUseCase: GetCharacterQuotesUseCase
) : ViewModel() {

    private val _quotesListLiveData = MutableLiveData<ResultWrapper<List<Quote>>>(
        ResultWrapper.InitialState())
    val quotesListLiveData: LiveData<ResultWrapper<List<Quote>>>
        get() = _quotesListLiveData

    fun requestCharacterQuotes(characterId: String) = CoroutineScope(Dispatchers.IO).launch {
        getCharacterQuotesUseCase.execute(characterId).collect {
            _quotesListLiveData.postValue(it)
        }
    }
}