package com.kotlin.mvvm.unittestdemo.ui.home.mappers

import com.kotlin.mvvm.unittestdemo.data.mappers.RickAndMortyListMapper
import com.kotlin.mvvm.unittestdemo.domain.entity.LocationEntity
import com.kotlin.mvvm.unittestdemo.ui.home.LocationHomeUiData
import javax.inject.Inject

class LocationHomeUiMapperImpl @Inject constructor() : RickAndMortyListMapper<LocationEntity, LocationHomeUiData> {
    override fun map(input: List<LocationEntity>?): List<LocationHomeUiData> {
        return input?.map {
            LocationHomeUiData(
                id = it.id,
                name = it.name,
                residents = it.residents
            )
        } ?: emptyList()
    }

}