package com.kotlin.mvvm.unittestdemo.data.mappers

import com.kotlin.mvvm.unittestdemo.data.dto.locations.Result
import com.kotlin.mvvm.unittestdemo.domain.entity.LocationEntity
import com.kotlin.mvvm.unittestdemo.utility.addCharactersIds
import javax.inject.Inject

class LocationListMapperImpl @Inject constructor() : RickAndMortyListMapper<Result,LocationEntity> {
    override fun map(input: List<Result>?): List<LocationEntity> {
        return input?.map {
            LocationEntity(
                id = it.id,
                name = it.name,
                residents = it.residents.addCharactersIds()
            )
        } ?: emptyList()
    }
}