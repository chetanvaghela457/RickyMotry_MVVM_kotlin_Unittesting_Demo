package com.kotlin.mvvm.unittestdemo.ui.home

import com.kotlin.mvvm.unittestdemo.characterList
import com.kotlin.mvvm.unittestdemo.ui.home.mappers.CharacterHomeUiMapperImpl
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

internal class CharacterHomeUiMapperImplTest {
    private val characterHomeUiMapperImpl = CharacterHomeUiMapperImpl()
    private lateinit var uiElements :List<CharacterHomeUiData>

    @Before
    fun setup(){
        uiElements = characterHomeUiMapperImpl.map(characterList)
    }

    @Test
    fun `when entity mapped is id correct`(){
        assertThat(uiElements[0].id).isEqualTo(characterList[0].id)
    }

    @Test
    fun `when entity mapped is name correct`(){
        assertThat(uiElements[0].name).isEqualTo(characterList[0].name)
    }

    @Test
    fun `when entity mapped is location correct`(){
        assertThat(uiElements[0].location).isEqualTo(characterList[0].location)
    }

    @Test
    fun `when entity mapped is image correct`(){
        assertThat(uiElements[0].image).isEqualTo(characterList[0].image)
    }

    @Test
    fun `when entity mapped is status correct`(){
        assertThat(uiElements[0].status).isEqualTo(characterList[0].status)
    }

    @Test
    fun `when entity mapped is origin correct`(){
        assertThat(uiElements[0].origin).isEqualTo(characterList[0].origin)
    }

    @Test
    fun `when entity mapped is species correct`(){
        assertThat(uiElements[0].species).isEqualTo(characterList[0].species)
    }

    @Test
    fun `when entity mapped is created correct`(){
        assertThat(uiElements[0].created).isEqualTo(characterList[0].created)
    }

    @Test
    fun `when entity mapped is episode correct`(){
        assertThat(uiElements[0].episode).isEqualTo(characterList[0].episode)
    }

    @Test
    fun `when entity mapped is gender correct`(){
        assertThat(uiElements[0].gender).isEqualTo(characterList[0].gender)
    }

    @Test
    fun `when entity mapped size of lists same`(){
        assertThat(uiElements.size).isEqualTo(characterList.size)
    }

    @Test
    fun `when input entity is null is result empty`(){
        val newUiElements = characterHomeUiMapperImpl.map(null)
        assert(newUiElements.isEmpty())
    }

}