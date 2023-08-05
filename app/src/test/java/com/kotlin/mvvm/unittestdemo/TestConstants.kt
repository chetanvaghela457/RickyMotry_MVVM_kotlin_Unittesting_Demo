package com.kotlin.mvvm.unittestdemo

import com.kotlin.mvvm.unittestdemo.data.dto.character.CharacterResponseItem
import com.kotlin.mvvm.unittestdemo.data.dto.character.Location
import com.kotlin.mvvm.unittestdemo.data.dto.character.Origin
import com.kotlin.mvvm.unittestdemo.data.dto.locations.Result
import com.kotlin.mvvm.unittestdemo.domain.entity.CharacterEntity
import com.kotlin.mvvm.unittestdemo.domain.entity.LocationEntity
import com.kotlin.mvvm.unittestdemo.ui.home.CharacterHomeUiData
import com.kotlin.mvvm.unittestdemo.ui.home.LocationHomeUiData
import com.google.common.annotations.VisibleForTesting

const val SAMPLE_LOCATION_RESPONSE_FILE_NAME = "LocationResponse.json"
const val SAMPLE_PAGE_NUMBER = 1

const val SAMPLE_CHARACTER_RESPONSE_FILE_NAME ="CharacterResponse.json"
val SAMPLE_IDS= listOf("1","2","3","4")



@VisibleForTesting
val locationEntity = LocationEntity(
    1,
    "Earth (C-137)",
    listOf(
        "https://rickandmortyapi.com/api/character/38",
        "https://rickandmortyapi.com/api/character/45",
        "https://rickandmortyapi.com/api/character/71",
        "https://rickandmortyapi.com/api/character/82",
        "https://rickandmortyapi.com/api/character/83",
        "https://rickandmortyapi.com/api/character/92",
        "https://rickandmortyapi.com/api/character/112",
        "https://rickandmortyapi.com/api/character/114",
        "https://rickandmortyapi.com/api/character/116",
        "https://rickandmortyapi.com/api/character/117",
        "https://rickandmortyapi.com/api/character/120",
        "https://rickandmortyapi.com/api/character/127",
        "https://rickandmortyapi.com/api/character/155",
        "https://rickandmortyapi.com/api/character/169",
        "https://rickandmortyapi.com/api/character/175",
        "https://rickandmortyapi.com/api/character/179",
        "https://rickandmortyapi.com/api/character/186",
        "https://rickandmortyapi.com/api/character/201",
        "https://rickandmortyapi.com/api/character/216",
        "https://rickandmortyapi.com/api/character/239",
        "https://rickandmortyapi.com/api/character/271",
        "https://rickandmortyapi.com/api/character/302",
        "https://rickandmortyapi.com/api/character/303",
        "https://rickandmortyapi.com/api/character/338",
        "https://rickandmortyapi.com/api/character/343",
        "https://rickandmortyapi.com/api/character/356",
        "https://rickandmortyapi.com/api/character/394"
    )
)
@VisibleForTesting
val characterEntity = CharacterEntity(
    "2017-11-04T18:48:46.250Z",
    "1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51",
    "Male",
    1,
    "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
    "Citadel of Ricks",
    "Rick Sanchez",
    "Earth (C-137)",
    "Human",
    "Alive"
)

val characterResponseItem = CharacterResponseItem(
    "2017-11-04T18:48:46.250Z",
    listOf("1"),
    "Male",
    1,
    "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
    Location(
        "Citadel of Ricks",
        "https://rickandmortyapi.com/api/location/3"
    ),
    "Rick Sanchez",
    Origin(
        "Earth (C-137)",
        "https://rickandmortyapi.com/api/location/1"
    ),
    "Human",
    "Alive",
    "",
    "https://rickandmortyapi.com/api/character/1",


)
val locationResult = Result(
    "2017-11-10T13:06:38.182Z",
    "unknown",
    2,
    "Abadango",
    listOf("https://rickandmortyapi.com/api/character/6"),
    "Cluster",
    "https://rickandmortyapi.com/api/location/2"
    )

@VisibleForTesting
val locationList = mutableListOf(locationEntity)

@VisibleForTesting
val characterList = mutableListOf(characterEntity)

@VisibleForTesting
val resultList = mutableListOf(locationResult)

val characterResponseItemList = mutableListOf(characterResponseItem)

val characterUiData = CharacterHomeUiData("2017-11-04T18:48:46.250Z",
    "1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51",
    "Male",
    1,
    "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
    "Citadel of Ricks",
    "Rick Sanchez",
    "Earth (C-137)",
    "Human",
    "Alive")
val characterUiDataList = mutableListOf(characterUiData)

val locationUiData=LocationHomeUiData(1,
    "Earth (C-137)",
    listOf(
        "https://rickandmortyapi.com/api/character/38",
        "https://rickandmortyapi.com/api/character/45",
        "https://rickandmortyapi.com/api/character/71",
        "https://rickandmortyapi.com/api/character/82",
        "https://rickandmortyapi.com/api/character/83",
        "https://rickandmortyapi.com/api/character/92",
        "https://rickandmortyapi.com/api/character/112",
        "https://rickandmortyapi.com/api/character/114",
        "https://rickandmortyapi.com/api/character/116",
        "https://rickandmortyapi.com/api/character/117",
        "https://rickandmortyapi.com/api/character/120",
        "https://rickandmortyapi.com/api/character/127",
        "https://rickandmortyapi.com/api/character/155",
        "https://rickandmortyapi.com/api/character/169",
        "https://rickandmortyapi.com/api/character/175",
        "https://rickandmortyapi.com/api/character/179",
        "https://rickandmortyapi.com/api/character/186",
        "https://rickandmortyapi.com/api/character/201",
        "https://rickandmortyapi.com/api/character/216",
        "https://rickandmortyapi.com/api/character/239",
        "https://rickandmortyapi.com/api/character/271",
        "https://rickandmortyapi.com/api/character/302",
        "https://rickandmortyapi.com/api/character/303",
        "https://rickandmortyapi.com/api/character/338",
        "https://rickandmortyapi.com/api/character/343",
        "https://rickandmortyapi.com/api/character/356",
        "https://rickandmortyapi.com/api/character/394"
    ))
val locationHomeUiDataList = mutableListOf(locationUiData)

enum class TestResponseEnum{
    ERROR,
    LOADING,
    SUCCESS
}
@VisibleForTesting
val apiException = Exception("Something went wrong")

