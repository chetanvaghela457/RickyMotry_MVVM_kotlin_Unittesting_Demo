package com.kotlin.mvvm.unittestdemo.domain

import com.kotlin.mvvm.unittestdemo.apiException
import com.kotlin.mvvm.unittestdemo.data.NetworkResponseState
import com.kotlin.mvvm.unittestdemo.domain.entity.LocationEntity
import com.kotlin.mvvm.unittestdemo.domain.usecase.location.GetLocationsUseCase
import com.kotlin.mvvm.unittestdemo.locationList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeGetLocationsUseCase : GetLocationsUseCase {
    private var showError = false
    fun updateShowError(showError:Boolean){
        this.showError = showError
    }

    override suspend fun invoke(pageNumber: Int): Flow<NetworkResponseState<List<LocationEntity>>> =
        flow {
            emit(NetworkResponseState.Loading)
            if (showError){
                emit(NetworkResponseState.Error(apiException))
            }else{
                emit(NetworkResponseState.Success(locationList))
            }
        }

}