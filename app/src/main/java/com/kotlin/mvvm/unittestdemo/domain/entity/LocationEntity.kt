package com.kotlin.mvvm.unittestdemo.domain.entity

data class LocationEntity(
    val id: Int,
    val name: String,
    val residents: List<String>
)