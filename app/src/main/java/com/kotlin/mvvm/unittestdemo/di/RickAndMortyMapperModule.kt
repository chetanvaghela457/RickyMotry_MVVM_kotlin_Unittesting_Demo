package com.kotlin.mvvm.unittestdemo.di

import com.kotlin.mvvm.unittestdemo.data.dto.character.CharacterResponseItem
import com.kotlin.mvvm.unittestdemo.data.mappers.RickAndMortyListMapper
import com.kotlin.mvvm.unittestdemo.data.mappers.LocationListMapperImpl
import com.kotlin.mvvm.unittestdemo.domain.entity.LocationEntity
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import com.kotlin.mvvm.unittestdemo.data.dto.locations.Result
import com.kotlin.mvvm.unittestdemo.data.mappers.CharacterListMapperImpl
import com.kotlin.mvvm.unittestdemo.domain.entity.CharacterEntity
import com.kotlin.mvvm.unittestdemo.ui.home.CharacterHomeUiData
import com.kotlin.mvvm.unittestdemo.ui.home.mappers.CharacterHomeUiMapperImpl
import com.kotlin.mvvm.unittestdemo.ui.home.LocationHomeUiData
import com.kotlin.mvvm.unittestdemo.ui.home.mappers.LocationHomeUiMapperImpl
import dagger.Binds
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class RickAndMortyMapperModule {
    @Binds
    @ViewModelScoped
    abstract fun bindRickAndMortyListMapper(locationListMapperImpl: LocationListMapperImpl):RickAndMortyListMapper<Result,LocationEntity>

    @Binds
    @ViewModelScoped
    abstract fun bindRickAndMortyLocationUiMapper(locationHomeUiMapperImpl: LocationHomeUiMapperImpl):RickAndMortyListMapper<LocationEntity, LocationHomeUiData>


    @Binds
    @ViewModelScoped
    abstract fun bindCharacterListMapper(characterListMapperImpl: CharacterListMapperImpl):RickAndMortyListMapper<CharacterResponseItem,CharacterEntity>


    @Binds
    @ViewModelScoped
    abstract fun bindCharacterHomeUiMapper(characterHomeUiMapperImpl: CharacterHomeUiMapperImpl):RickAndMortyListMapper<CharacterEntity, CharacterHomeUiData>

}