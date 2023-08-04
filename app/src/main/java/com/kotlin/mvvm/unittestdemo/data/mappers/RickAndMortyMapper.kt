package com.kotlin.mvvm.unittestdemo.data.mappers

interface RickAndMortyMapper<I,O> {
    fun map(input:I?):O
}