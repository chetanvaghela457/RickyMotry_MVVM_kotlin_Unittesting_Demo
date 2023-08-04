package com.kotlin.mvvm.unittestdemo.ui.home

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharacterHomeUiData (
    val created: String,
    val episode: String,
    val gender: String,
    val id: Int,
    val image: String,
    val location: String,
    val name: String,
    val origin: String,
    val species: String,
    val status: String,
        ) : Parcelable