package com.devlabs.domain.repository

interface AuthRepository {
    fun savePin(pin: String)
    fun hasPinSaved(): Boolean
    fun authPin(pin: String): Boolean
}