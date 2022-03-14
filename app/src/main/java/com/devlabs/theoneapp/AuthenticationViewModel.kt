package com.devlabs.theoneapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devlabs.domain.entity.ResultWrapper
import com.devlabs.domain.usecase.AuthPinUseCase
import com.devlabs.domain.usecase.HasPinUseCase
import com.devlabs.domain.usecase.SavePinUseCase

class AuthenticationViewModel(
    private val savePinUseCase: SavePinUseCase,
    private val hasPinUseCase: HasPinUseCase,
    private val authPinUseCase: AuthPinUseCase
) : ViewModel() {

    private val _loggedInLiveData = MutableLiveData<Boolean>()
    val loggedInLiveData: LiveData<Boolean>
        get() = _loggedInLiveData

    private val _hasPinLiveData = MutableLiveData<Boolean>()
    val hasPinLiveData: LiveData<Boolean>
        get() = _hasPinLiveData

    fun verifyHasPin() {
        _hasPinLiveData.postValue(hasPinUseCase.execute())
    }

    fun savePin(pin: String) {
        if (hasPinUseCase.execute()) {
            _loggedInLiveData.postValue(authPinUseCase.execute(pin))
        } else {
            savePinUseCase.execute(pin)
        }
    }
}