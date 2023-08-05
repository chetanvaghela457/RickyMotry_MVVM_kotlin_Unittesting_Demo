package com.kotlin.mvvm.unittestdemo.data.repository

import com.kotlin.mvvm.unittestdemo.*
import com.kotlin.mvvm.unittestdemo.data.NetworkResponseState
import com.kotlin.mvvm.unittestdemo.data.mappers.CharacterListMapperImpl
import com.kotlin.mvvm.unittestdemo.data.mappers.LocationListMapperImpl
import com.kotlin.mvvm.unittestdemo.domain.entity.CharacterEntity
import com.kotlin.mvvm.unittestdemo.domain.entity.LocationEntity
import com.kotlin.mvvm.unittestdemo.domain.repository.RickAndMortyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeRepository(
    private val locationMapper:LocationListMapperImpl,
    private val characterMapper:CharacterListMapperImpl
) : RickAndMortyRepository{
    private var testRequest = TestResponseEnum.LOADING

    fun setRequest(testRequest:TestResponseEnum){
        this.testRequest = testRequest
    }
    override suspend fun getLocations(pageNumber: Int): Flow<NetworkResponseState<List<LocationEntity>>> =
        flow {
            emit(NetworkResponseState.Loading)
            when(testRequest){
                TestResponseEnum.LOADING-> emit(NetworkResponseState.Loading)
                TestResponseEnum.ERROR-> emit(NetworkResponseState.Error(Exception("Hata Oluştu")))
                TestResponseEnum.SUCCESS -> emit(NetworkResponseState.Success(locationMapper.map(
                    resultList)))
            }
        }

    override suspend fun getCharactersById(characterIds: List<String>): Flow<NetworkResponseState<List<CharacterEntity>>> =
        flow {
            emit(NetworkResponseState.Loading)
            when(testRequest){
                TestResponseEnum.LOADING -> emit(NetworkResponseState.Loading)
                TestResponseEnum.ERROR -> emit(NetworkResponseState.Error(Exception("Hata Oluştu")))
                TestResponseEnum.SUCCESS -> emit(NetworkResponseState.Success(characterMapper.map(
                    characterResponseItemList
                )))
            }
        }


    override fun getIsAppFirstOpenState(): Flow<Boolean> {
        TODO("Not yet implemented")
    }

    override suspend fun saveAppFirstOpenState(state: Boolean) {
        TODO("Not yet implemented")
    }
}