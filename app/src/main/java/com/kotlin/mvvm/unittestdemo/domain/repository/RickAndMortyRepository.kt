package com.kotlin.mvvm.unittestdemo.domain.repository

import com.kotlin.mvvm.unittestdemo.data.NetworkResponseState
import com.kotlin.mvvm.unittestdemo.domain.entity.CharacterEntity
import com.kotlin.mvvm.unittestdemo.domain.entity.LocationEntity
import kotlinx.coroutines.flow.Flow

interface RickAndMortyRepository {
    suspend fun getLocations(pageNumber:Int): Flow<NetworkResponseState<List<LocationEntity>>>

    suspend fun getCharactersById(characterIds:List<String>):Flow<NetworkResponseState<List<CharacterEntity>>>

    fun getIsAppFirstOpenState():Flow<Boolean>

    suspend fun saveAppFirstOpenState(state:Boolean)
}