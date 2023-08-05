package com.kotlin.mvvm.unittestdemo.data.api

import com.kotlin.mvvm.unittestdemo.SAMPLE_CHARACTER_RESPONSE_FILE_NAME
import com.kotlin.mvvm.unittestdemo.SAMPLE_IDS
import com.kotlin.mvvm.unittestdemo.SAMPLE_LOCATION_RESPONSE_FILE_NAME
import com.kotlin.mvvm.unittestdemo.SAMPLE_PAGE_NUMBER
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RickAndMortyApiTest {
    private lateinit var api: RickAndMortyApi
    private val mockWebServer = MockWebServer()

    @Before
    fun setup(){
        mockWebServer.start(3000)
        api = Retrofit.Builder()
            .baseUrl(mockWebServer.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RickAndMortyApi::class.java)
    }

    @Test
    fun `when search query page is response not null`(){
        runBlocking {
            enqueueResponse(SAMPLE_LOCATION_RESPONSE_FILE_NAME)
            val response = api.getLocations(SAMPLE_PAGE_NUMBER)
            val request = mockWebServer.takeRequest()
            assertThat(response).isNotNull()
        }
    }

    @Test
    fun `when character id 1,2,3,4 is response not null`(){
        runBlocking {
            enqueueResponse(SAMPLE_CHARACTER_RESPONSE_FILE_NAME)
            val response = api.getCharactersById(SAMPLE_IDS)
            val request = mockWebServer.takeRequest()
            assertThat(response).isNotNull()
        }
    }

    @Test
    fun `when search query page is request path same`(){
        runBlocking {
            enqueueResponse(SAMPLE_LOCATION_RESPONSE_FILE_NAME)
            val response = api.getLocations(SAMPLE_PAGE_NUMBER)
            val request = mockWebServer.takeRequest()
            assertThat(request.path).isEqualTo("/location?page=${SAMPLE_PAGE_NUMBER}")
        }
    }

    @Test
    fun `when character id 1,2,3,4 is request path same`(){
        runBlocking {
            enqueueResponse(SAMPLE_CHARACTER_RESPONSE_FILE_NAME)
            val response = api.getCharactersById(SAMPLE_IDS)
            val request = mockWebServer.takeRequest()
            println(request.path)
            assertThat(request.path).isEqualTo("/character/[1,%202,%203,%204]")
        }
    }

    @Test
    fun `when search query page is first item id expected`(){
        runBlocking {
            enqueueResponse(SAMPLE_LOCATION_RESPONSE_FILE_NAME)
            val response = api.getLocations(SAMPLE_PAGE_NUMBER)
            val firstItem= response.results.first()
            assertThat(firstItem.id).isEqualTo(1)
        }
    }
    @Test
    fun `when character id 3 is item id expected`(){
        runBlocking {
            enqueueResponse(SAMPLE_CHARACTER_RESPONSE_FILE_NAME)
            val response = api.getCharactersById(SAMPLE_IDS)
            val firstItem= response[2]
            assertThat(firstItem.id).isEqualTo(3)
        }
    }

    private fun enqueueResponse(fileName:String){
        javaClass.classLoader?.let {
            val inputStream = it.getResourceAsStream(fileName)
            val source = inputStream.source().buffer()
            val mockResponse = MockResponse()
            mockResponse.setBody(source.readString(Charsets.UTF_8))
            mockWebServer.enqueue(mockResponse)
        }
    }


    @After
    fun shutDown(){
        mockWebServer.shutdown()
    }
}