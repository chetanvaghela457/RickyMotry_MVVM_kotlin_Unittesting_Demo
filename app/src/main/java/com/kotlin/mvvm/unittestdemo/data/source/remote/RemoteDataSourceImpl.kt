package com.kotlin.mvvm.unittestdemo.data.source.remote

import com.kotlin.mvvm.unittestdemo.data.NetworkResponseState
import com.kotlin.mvvm.unittestdemo.data.api.RickAndMortyApi
import com.kotlin.mvvm.unittestdemo.data.dto.character.CharacterResponseItem
import com.kotlin.mvvm.unittestdemo.data.dto.locations.Result
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val api: RickAndMortyApi) :
    RemoteDataSource {
    override suspend fun getLocations(pageNumber:Int): NetworkResponseState<List<Result>> =
        try {
            val response =  api.getLocations(pageNumber)
            NetworkResponseState.Success(response.results)
        }catch (e:Exception){
            NetworkResponseState.Error(e)
        }

    override suspend fun getCharactersById(characterIds: List<String>): NetworkResponseState<List<CharacterResponseItem>> =
        try {
            val response = api.getCharactersById(characterIds)
            NetworkResponseState.Success(response)
        } catch (e: Exception) {
            NetworkResponseState.Error(e)
        }


}