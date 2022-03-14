package com.devlabs.domain.usecase

import com.devlabs.domain.repository.AuthRepository

interface AuthPinUseCase {
    fun execute(pin: String): Boolean
}

class AuthPinUseCaseImpl(
    private val authRepository: AuthRepository
) : AuthPinUseCase {
    override fun execute(pin: String): Boolean = authRepository.authPin(pin)
}