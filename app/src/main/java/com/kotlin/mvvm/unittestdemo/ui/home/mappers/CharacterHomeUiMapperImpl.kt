package com.kotlin.mvvm.unittestdemo.ui.home.mappers

import com.kotlin.mvvm.unittestdemo.data.mappers.RickAndMortyListMapper
import com.kotlin.mvvm.unittestdemo.domain.entity.CharacterEntity
import com.kotlin.mvvm.unittestdemo.ui.home.CharacterHomeUiData
import javax.inject.Inject


class CharacterHomeUiMapperImpl @Inject constructor() :RickAndMortyListMapper<CharacterEntity, CharacterHomeUiData> {
    override fun map(input: List<CharacterEntity>?): List<CharacterHomeUiData> {
        return input?.map {
            CharacterHomeUiData(
                created = it.created,
                episode = it.episode,
                gender = it.gender,
                id = it.id,
                image = it.image,
                location = it.location,
                name = it.name,
                origin = it.origin,
                species = it.species,
                status = it.status
            )
        } ?: emptyList()
    }

}