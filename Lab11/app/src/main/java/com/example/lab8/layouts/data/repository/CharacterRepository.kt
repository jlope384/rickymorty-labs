package com.example.lab8.layouts.data.repository

import com.example.lab8.layouts.data.local.daos.CharacterDao
import com.example.lab8.layouts.data.local.entity.CharacterEntity
import com.example.lab8.layouts.data.local.domain.Character

class CharacterRepository(private val characterDao: CharacterDao) {

    suspend fun syncCharacters(characters: List<CharacterEntity>) {
        characterDao.insertAll(characters)
    }

    suspend fun getAllCharacters(): List<Character> {
        return characterDao.getAllCharacters().map { entity ->
            Character(
                id = entity.id,
                name = entity.name,
                image = entity.image,
                species = entity.species,
                status = entity.status,
                gender = entity.gender
            )
        }
    }

    suspend fun getCharacterById(id: Int): Character? {
        val entity = characterDao.getCharacterById(id)
        return entity?.let {
            Character(
                id = it.id,
                name = it.name,
                image = it.image,
                species = it.species,
                status = it.status,
                gender = it.gender
            )
        }
    }
}
