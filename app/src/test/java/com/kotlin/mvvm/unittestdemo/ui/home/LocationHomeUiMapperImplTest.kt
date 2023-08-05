package com.kotlin.mvvm.unittestdemo.ui.home

import com.kotlin.mvvm.unittestdemo.locationList
import com.kotlin.mvvm.unittestdemo.ui.home.mappers.LocationHomeUiMapperImpl
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

internal class LocationHomeUiMapperImplTest {

    private val locationHomeUiMapperImpl = LocationHomeUiMapperImpl()
    private lateinit var uiElements: List<LocationHomeUiData>

    @Before
    fun setup(){
        uiElements = locationHomeUiMapperImpl.map(locationList)
    }

    @Test
    fun `when entity mapped is id correct`(){
        assertThat(uiElements[0].id).isEqualTo(locationList[0].id)
    }

    @Test
    fun `when entity mapped is name correct`(){
        assertThat(uiElements[0].name).isEqualTo(locationList[0].name)
    }

    @Test
    fun `when entity mapped is residents correct`(){
        assertThat(uiElements[0].residents).isEqualTo(locationList[0].residents)
    }

    @Test
    fun `when entity mapped size of lists same`(){
        assertThat(uiElements.size).isEqualTo(locationList.size)
    }

    @Test
    fun `when input entity is null is result empty`(){
        val newUiElements = locationHomeUiMapperImpl.map(null)
        assert(newUiElements.isEmpty())
    }
}