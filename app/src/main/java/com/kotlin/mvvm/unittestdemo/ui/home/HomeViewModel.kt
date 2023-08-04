package com.kotlin.mvvm.unittestdemo.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotlin.mvvm.unittestdemo.R
import com.kotlin.mvvm.unittestdemo.data.NetworkResponseState
import com.kotlin.mvvm.unittestdemo.data.mappers.RickAndMortyListMapper
import com.kotlin.mvvm.unittestdemo.domain.entity.CharacterEntity
import com.kotlin.mvvm.unittestdemo.domain.entity.LocationEntity
import com.kotlin.mvvm.unittestdemo.domain.usecase.character.GetCharactersByIdUseCase
import com.kotlin.mvvm.unittestdemo.domain.usecase.location.GetLocationsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getLocationsUseCase: GetLocationsUseCase,
    private val getCharactersByIdUseCase: GetCharactersByIdUseCase,
    private val locationHomeUiMapper: RickAndMortyListMapper<LocationEntity, LocationHomeUiData>,
    private val characterHomeUiMapper: RickAndMortyListMapper<CharacterEntity, CharacterHomeUiData>
) : ViewModel() {

    private var _locationHomeUiState = MutableLiveData<HomeUiState<LocationHomeUiData>>()
    val locationHomeUiState: LiveData<HomeUiState<LocationHomeUiData>> get() = _locationHomeUiState

    var locationPaginateNumber = 1

    private var _characterHomeUiState = MutableLiveData<HomeUiState<CharacterHomeUiData>>()
    val characterHomeUiState: LiveData<HomeUiState<CharacterHomeUiData>> get() = _characterHomeUiState

    fun getLocations() {
        viewModelScope.launch {
            getLocationsUseCase.invoke(locationPaginateNumber).collectLatest { response ->
                when (response) {
                    is NetworkResponseState.Success -> {
                        _locationHomeUiState.postValue(
                            handleLocationResponse(
                                HomeUiState.Success(
                                    locationHomeUiMapper.map(response.result)
                                )
                            )
                        )
                    }
                    is NetworkResponseState.Error -> {
                        _locationHomeUiState.postValue(HomeUiState.Error(R.string.error))
                    }
                    is NetworkResponseState.Loading -> {
                        _locationHomeUiState.postValue(HomeUiState.Loading)
                    }
                }
            }
        }
    }

    private fun handleLocationResponse(location: HomeUiState.Success<LocationHomeUiData>): HomeUiState<LocationHomeUiData> {
        location.let { resultResponse ->
            locationPaginateNumber++
            return HomeUiState.Success(resultResponse.data)
        }
    }


    fun getCharactersById(charactersId: List<String>) {
        viewModelScope.launch {
            getCharactersByIdUseCase.invoke(charactersId).collectLatest { response ->
                when (response) {
                    is NetworkResponseState.Success -> {
                        _characterHomeUiState.postValue(
                            HomeUiState.Success(
                                characterHomeUiMapper.map(
                                    response.result
                                )
                            )
                        )
                    }
                    is NetworkResponseState.Error -> {
                        _characterHomeUiState.postValue(HomeUiState.Error(R.string.error))
                    }
                    is NetworkResponseState.Loading -> {
                        _characterHomeUiState.postValue(HomeUiState.Loading)
                    }
                }

            }
        }
    }

}