package com.kotlin.mvvm.unittestdemo.data.mappers

import com.kotlin.mvvm.unittestdemo.data.dto.character.CharacterResponseItem
import com.kotlin.mvvm.unittestdemo.domain.entity.CharacterEntity
import com.kotlin.mvvm.unittestdemo.utility.dateTimeConverter
import com.kotlin.mvvm.unittestdemo.utility.getEpisodes
import javax.inject.Inject

class CharacterListMapperImpl @Inject constructor() : RickAndMortyListMapper<CharacterResponseItem, CharacterEntity> {
    override fun map(input: List<CharacterResponseItem>?): List<CharacterEntity> {
        return input?.map {
            CharacterEntity(
                created = it.created.dateTimeConverter(),
                episode = it.episode.getEpisodes(),
                gender = it.gender,
                id = it.id,
                image = it.image,
                location = it.location.name,
                name = it.name,
                origin = it.origin.name,
                species = it.species,
                status = it.status
            )
        } ?: emptyList()
    }
}