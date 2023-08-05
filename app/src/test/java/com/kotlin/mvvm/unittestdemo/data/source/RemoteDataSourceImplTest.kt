package com.kotlin.mvvm.unittestdemo.data.source

import com.kotlin.mvvm.unittestdemo.SAMPLE_IDS
import com.kotlin.mvvm.unittestdemo.SAMPLE_PAGE_NUMBER
import com.kotlin.mvvm.unittestdemo.characterResponseItemList
import com.kotlin.mvvm.unittestdemo.data.NetworkResponseState
import com.kotlin.mvvm.unittestdemo.data.api.RickAndMortyApi
import com.kotlin.mvvm.unittestdemo.data.dto.character.CharacterResponse
import com.kotlin.mvvm.unittestdemo.data.dto.locations.LocationResponse
import com.kotlin.mvvm.unittestdemo.data.source.remote.RemoteDataSourceImpl
import com.kotlin.mvvm.unittestdemo.resultList
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class RemoteDataSourceImplTest {

    @Mock
    private lateinit var rickAndMortyApi: RickAndMortyApi

    private lateinit var remoteDataSourceImpl: RemoteDataSourceImpl

    @Before
    fun setup(){
        MockitoAnnotations.openMocks(this)
        remoteDataSourceImpl = RemoteDataSourceImpl(rickAndMortyApi)
    }

    @Test
    fun `when location list returned is state success`(){
        runBlocking {
            Mockito.`when`(rickAndMortyApi.getLocations(SAMPLE_PAGE_NUMBER))
                .thenReturn(
                    LocationResponse(null, resultList)
                )
            val response = remoteDataSourceImpl.getLocations(SAMPLE_PAGE_NUMBER)
            assertThat(response).isInstanceOf(NetworkResponseState.Success::class.java)
        }
    }

    @Test
    fun  `when rickAndMorty api location returned null is state failure`(){
        runBlocking {
            Mockito.`when`(rickAndMortyApi.getLocations(SAMPLE_PAGE_NUMBER))
                .thenReturn(
                    null
                )
            val response = remoteDataSourceImpl.getLocations(SAMPLE_PAGE_NUMBER)
            assertThat(response).isInstanceOf(NetworkResponseState.Error::class.java)
        }
    }

    @Test
    fun `when character list returned is state success`(){
        runBlocking {
            Mockito.`when`(rickAndMortyApi.getCharactersById(SAMPLE_IDS))
                .thenReturn(
                    CharacterResponse()
                )
            val response = remoteDataSourceImpl.getCharactersById(SAMPLE_IDS)
            assertThat(response).isInstanceOf(NetworkResponseState.Success::class.java)
        }
    }

    @Test
    fun  `when rickAndMorty api character returned null is state SUCCESS`(){
        runBlocking {
            Mockito.`when`(rickAndMortyApi.getCharactersById(SAMPLE_IDS))
                .thenReturn(
                    null
                )
            val response = remoteDataSourceImpl.getCharactersById(SAMPLE_IDS)
            assertThat(response).isInstanceOf(NetworkResponseState.Success::class.java)
        }
    }
}