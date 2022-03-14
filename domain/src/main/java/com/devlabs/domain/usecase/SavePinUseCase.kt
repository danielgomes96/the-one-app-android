package com.devlabs.domain.usecase

import com.devlabs.domain.repository.AuthRepository

interface SavePinUseCase {
    fun execute(pin: String)
}

class SavePinUseCaseImpl(
    private val authRepository: AuthRepository
) : SavePinUseCase {
    override fun execute(pin: String) {
        authRepository.savePin(pin)
    }
}