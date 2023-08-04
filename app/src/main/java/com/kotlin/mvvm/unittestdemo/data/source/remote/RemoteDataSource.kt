package com.kotlin.mvvm.unittestdemo.data.source.remote

import com.kotlin.mvvm.unittestdemo.data.NetworkResponseState
import com.kotlin.mvvm.unittestdemo.data.dto.character.CharacterResponseItem
import com.kotlin.mvvm.unittestdemo.data.dto.locations.Result


interface RemoteDataSource {

    suspend fun getLocations(pageNumber:Int): NetworkResponseState<List<Result>>

    suspend fun getCharactersById(characterIds:List<String>): NetworkResponseState<List<CharacterResponseItem>>
}