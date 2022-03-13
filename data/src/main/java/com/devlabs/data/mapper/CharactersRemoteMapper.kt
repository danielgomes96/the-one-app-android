package com.devlabs.data.mapper

import com.devlabs.data.dto.DTOCharactersResponse
import com.devlabs.domain.entity.Movie
import com.devlabs.domain.entity.Character

class CharactersRemoteMapper : BaseMapper<DTOCharactersResponse, List<Character>>() {
    override fun transform(entity: DTOCharactersResponse): List<Character> {
        val charactersList = arrayListOf<Character>()
        entity.charactersList.map {
            charactersList.add(
                Character(
                    it.id ?: "",
                    it.name ?: "No name",
                    it.gender ?: "No gender",
                    it.birth ?: "No age"
                )
            )
        }
        return charactersList
    }
}