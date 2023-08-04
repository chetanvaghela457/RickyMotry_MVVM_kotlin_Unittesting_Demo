package com.kotlin.mvvm.unittestdemo.data.dto.locations


import com.google.gson.annotations.SerializedName

data class LocationResponse(
    @SerializedName("info")
    val info: Info?,
    @SerializedName("results")
    val results: List<Result>
)