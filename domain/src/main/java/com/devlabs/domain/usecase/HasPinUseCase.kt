package com.devlabs.domain.usecase

import com.devlabs.domain.repository.AuthRepository

interface HasPinUseCase {
    fun execute(): Boolean
}

class HasPinUseCaseImpl(
    private val authRepository: AuthRepository
) : HasPinUseCase {
    override fun execute(): Boolean = authRepository.hasPinSaved()
}