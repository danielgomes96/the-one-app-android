package com.devlabs.data.mapper

import com.devlabs.data.dto.DTOCharacter
import com.devlabs.data.dto.DTOCharactersResponse
import com.devlabs.domain.entity.Character
import junit.framework.TestCase
import org.junit.Test

class CharactersMapperTest {
    private val charactersMapper by lazy {
        CharactersRemoteMapper()
    }
    companion object {
        private val CHARACTERS_DTO_LIST = listOf<DTOCharacter>(
            DTOCharacter(id = "5cd99d4bde30eff6ebccfc15", name = "Frodo", gender = "Male", birth = "No age")
        )
        private val DTO_CHARACTERS_NULL_FIELDS = listOf<DTOCharacter>(
            DTOCharacter(id = null, name = null, gender = null, birth = null)
        )
        val FAKE_DTO_CHARACTERS = DTOCharactersResponse(CHARACTERS_DTO_LIST)
        val FAKE_DTO_CHARACTERS_NULL_FIELDS = DTOCharactersResponse(DTO_CHARACTERS_NULL_FIELDS)
        val FAKE_CHARACTER = Character(id = "5cd99d4bde30eff6ebccfc15", name = "Frodo", gender = "Male", age = "No age")
    }

    @Test
    fun `GIVEN a fake response WHEN transforming it to entity THEN get proper character object`() {
        // Given
        // When
        val transformedMovie = charactersMapper.transform(FAKE_DTO_CHARACTERS)

        // Then
        TestCase.assertEquals(FAKE_CHARACTER.id, transformedMovie[0].id)
        TestCase.assertEquals(FAKE_CHARACTER.name, transformedMovie[0].name)
        TestCase.assertEquals(FAKE_CHARACTER.gender, transformedMovie[0].gender)
        TestCase.assertEquals(FAKE_CHARACTER.age, transformedMovie[0].age)
    }

    @Test
    fun `GIVEN a response with null properties WHEN transforming it to entity THEN get empty properties fields`() {
        // Given
        // When
        val transformedCharacters = charactersMapper.transform(FAKE_DTO_CHARACTERS_NULL_FIELDS)

        // Then
        TestCase.assertEquals(transformedCharacters[0].id, "")
        TestCase.assertEquals(transformedCharacters[0].name, "No name")
        TestCase.assertEquals(transformedCharacters[0].age, "No age")
        TestCase.assertEquals(transformedCharacters[0].gender, "No gender")
    }
}