package com.devlabs.domain.entity

import java.io.Serializable

data class Character(
    val id: String,
    val name: String,
    val gender: String = "No gender",
    val age: String = "No age"
): Serializable