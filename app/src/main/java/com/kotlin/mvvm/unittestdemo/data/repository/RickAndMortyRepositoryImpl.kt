package com.kotlin.mvvm.unittestdemo.data.repository

import com.kotlin.mvvm.unittestdemo.data.NetworkResponseState
import com.kotlin.mvvm.unittestdemo.data.dto.character.CharacterResponseItem
import com.kotlin.mvvm.unittestdemo.data.dto.locations.Result
import com.kotlin.mvvm.unittestdemo.data.mappers.RickAndMortyListMapper
import com.kotlin.mvvm.unittestdemo.data.source.remote.RemoteDataSource
import com.kotlin.mvvm.unittestdemo.data.source.local.LocalDataSource
import com.kotlin.mvvm.unittestdemo.di.IoDispatcher
import com.kotlin.mvvm.unittestdemo.domain.entity.CharacterEntity
import com.kotlin.mvvm.unittestdemo.domain.entity.LocationEntity
import com.kotlin.mvvm.unittestdemo.domain.repository.RickAndMortyRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RickAndMortyRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource:LocalDataSource,
    private val locationListMapper:RickAndMortyListMapper<Result,LocationEntity>,
    private val characterListMapper:RickAndMortyListMapper<CharacterResponseItem,CharacterEntity>,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : RickAndMortyRepository {
    override suspend fun getLocations(pageNumber:Int): Flow<NetworkResponseState<List<LocationEntity>>> =
        flow {
            emit(NetworkResponseState.Loading)
            when(val response = remoteDataSource.getLocations(pageNumber)){
                is NetworkResponseState.Error -> emit(response)
                NetworkResponseState.Loading->Unit
                is NetworkResponseState.Success -> emit(
                    NetworkResponseState.Success(
                        locationListMapper.map(
                            response.result
                        )
                    )
                )
            }
        }.flowOn(ioDispatcher)

    override suspend fun getCharactersById(characterIds: List<String>): Flow<NetworkResponseState<List<CharacterEntity>>> =
        flow {
            emit(NetworkResponseState.Loading)
            when(val response = remoteDataSource.getCharactersById(characterIds)){
                is NetworkResponseState.Error->emit(response)
                NetworkResponseState.Loading->Unit
                is NetworkResponseState.Success->{ emit(
                    NetworkResponseState.Success(
                        characterListMapper.map(
                            response.result
                        )
                    )
                )
                }
            }
        }.flowOn(ioDispatcher)

    override fun getIsAppFirstOpenState(): Flow<Boolean> =
        localDataSource.getIsAppFirstOpenState()

    override suspend fun saveAppFirstOpenState(state:Boolean) {
        localDataSource.saveAppFirstOpenState(state)
    }


}