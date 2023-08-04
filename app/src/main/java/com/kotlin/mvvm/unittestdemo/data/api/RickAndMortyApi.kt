package com.kotlin.mvvm.unittestdemo.data.api


import com.kotlin.mvvm.unittestdemo.data.dto.character.CharacterResponse
import com.kotlin.mvvm.unittestdemo.data.dto.locations.LocationResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyApi {
    @GET("location")
    suspend fun getLocations(@Query("page") pageNumber:Int) : LocationResponse

    @GET("character/{id}")
    suspend fun getCharactersById(@Path("id") id:List<String>) : CharacterResponse
}