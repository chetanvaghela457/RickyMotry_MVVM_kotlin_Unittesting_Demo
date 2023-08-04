package com.kotlin.mvvm.unittestdemo.domain.usecase.location

import com.kotlin.mvvm.unittestdemo.data.NetworkResponseState
import com.kotlin.mvvm.unittestdemo.domain.entity.LocationEntity
import com.kotlin.mvvm.unittestdemo.domain.repository.RickAndMortyRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLocationsUseCaseImpl @Inject constructor(
    private val repository: RickAndMortyRepository
) : GetLocationsUseCase {
    override suspend fun invoke(pageNumber:Int): Flow<NetworkResponseState<List<LocationEntity>>> =
        repository.getLocations(pageNumber)
}