package com.kotlin.mvvm.unittestdemo.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.kotlin.mvvm.unittestdemo.*
import com.kotlin.mvvm.unittestdemo.data.NetworkResponseState
import com.kotlin.mvvm.unittestdemo.domain.FakeGetCharactersByIdUseCase
import com.kotlin.mvvm.unittestdemo.domain.FakeGetLocationsUseCase
import com.kotlin.mvvm.unittestdemo.ui.home.mappers.CharacterHomeUiMapperImpl
import com.kotlin.mvvm.unittestdemo.ui.home.mappers.LocationHomeUiMapperImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.verify

class HomeViewModelTest {
    @Mock
    private lateinit var getLocationsUsecase:FakeGetLocationsUseCase

    @Mock
    private lateinit var getCharactersByIdUseCase: FakeGetCharactersByIdUseCase

    @Mock
    private lateinit var observerLocation:Observer<HomeUiState<LocationHomeUiData>>

    @Mock
    private lateinit var observerCharacter:Observer<HomeUiState<CharacterHomeUiData>>

    private val locationHomeUiMapperImpl = LocationHomeUiMapperImpl()

    private val characterHomeUiMapperImpl = CharacterHomeUiMapperImpl()

    private lateinit var viewModel:HomeViewModel

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup(){
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(Dispatchers.Unconfined)
        viewModel = HomeViewModel(getLocationsUsecase,getCharactersByIdUseCase,locationHomeUiMapperImpl,characterHomeUiMapperImpl)
        viewModel.locationHomeUiState.observeForever(observerLocation)
        viewModel.characterHomeUiState.observeForever(observerCharacter)
    }

    @Test
    fun `when location use case returned downloading is live data state downloading`(){
        runBlocking {
            Mockito.`when`(getLocationsUsecase.invoke(SAMPLE_PAGE_NUMBER))
                .thenReturn(flow {
                    emit(NetworkResponseState.Loading)
                })
            viewModel.getLocations()
            verify(observerLocation).onChanged(HomeUiState.Loading)
        }
    }
    @Test
    fun `when character use case returned downloading is live data state downloading`(){
        runBlocking {
            Mockito.`when`(getCharactersByIdUseCase.invoke(SAMPLE_IDS))
                .thenReturn(flow {
                    emit(NetworkResponseState.Loading)
                })
            viewModel.getCharactersById(SAMPLE_IDS)
            verify(observerCharacter).onChanged(HomeUiState.Loading)
        }
    }

    @Test
    fun `when location use case returned downloading is live data state success`(){
        runBlocking {
            Mockito.`when`(getLocationsUsecase.invoke(SAMPLE_PAGE_NUMBER))
                .thenReturn(flow {
                    emit(NetworkResponseState.Loading)
                    emit(NetworkResponseState.Success(locationList))
                })
            viewModel.getLocations()
            verify(observerLocation).onChanged(HomeUiState.Loading)
            verify(observerLocation).onChanged(HomeUiState.Success(locationHomeUiDataList))

        }
    }

    @Test
    fun `when character use case returned downloading is live data state success`(){
        runBlocking {
            Mockito.`when`(getCharactersByIdUseCase.invoke(SAMPLE_IDS))
                .thenReturn(flow {
                    emit(NetworkResponseState.Loading)
                    emit(NetworkResponseState.Success(characterList))
                })
            viewModel.getCharactersById(SAMPLE_IDS)
            verify(observerCharacter).onChanged(HomeUiState.Loading)
            verify(observerCharacter).onChanged(HomeUiState.Success(characterUiDataList))

        }
    }

    @Test
    fun `when location use case returned downloading is live data state error`(){
        runBlocking {
            Mockito.`when`(getLocationsUsecase.invoke(SAMPLE_PAGE_NUMBER))
                .thenReturn(flow {
                    emit(NetworkResponseState.Loading)
                    emit(NetworkResponseState.Error(apiException))
                })
            viewModel.getLocations()
            verify(observerLocation).onChanged(HomeUiState.Loading)
            verify(observerLocation).onChanged(HomeUiState.Error(R.string.error))
        }
    }
    @Test
    fun `when character use case returned downloading is live data state error`(){
        runBlocking {
            Mockito.`when`(getCharactersByIdUseCase.invoke(SAMPLE_IDS))
                .thenReturn(flow {
                    emit(NetworkResponseState.Loading)
                    emit(NetworkResponseState.Error(apiException))
                })
            viewModel.getCharactersById(SAMPLE_IDS)
            verify(observerCharacter).onChanged(HomeUiState.Loading)
            verify(observerCharacter).onChanged(HomeUiState.Error(R.string.error))
        }
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun shutdown(){
        Dispatchers.resetMain()
        viewModel.locationHomeUiState.removeObserver(observerLocation)
        viewModel.characterHomeUiState.removeObserver(observerCharacter)
    }


}