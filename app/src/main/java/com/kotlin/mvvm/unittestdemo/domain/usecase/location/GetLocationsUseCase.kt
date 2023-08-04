package com.kotlin.mvvm.unittestdemo.domain.usecase.location

import com.kotlin.mvvm.unittestdemo.data.NetworkResponseState
import com.kotlin.mvvm.unittestdemo.domain.entity.LocationEntity
import kotlinx.coroutines.flow.Flow

interface GetLocationsUseCase {
    suspend operator fun invoke(pageNumber:Int): Flow<NetworkResponseState<List<LocationEntity>>>
}