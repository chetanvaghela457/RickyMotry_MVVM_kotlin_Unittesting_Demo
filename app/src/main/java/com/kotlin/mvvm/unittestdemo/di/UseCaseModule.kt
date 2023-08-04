package com.kotlin.mvvm.unittestdemo.di

import com.kotlin.mvvm.unittestdemo.domain.usecase.character.GetCharactersByIdUseCase
import com.kotlin.mvvm.unittestdemo.domain.usecase.character.GetCharactersByIdUseCaseImpl
import com.kotlin.mvvm.unittestdemo.domain.usecase.location.GetLocationsUseCase
import com.kotlin.mvvm.unittestdemo.domain.usecase.location.GetLocationsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class UseCaseModule {

    @Binds
    @ViewModelScoped
    abstract fun bindGetLocationsUseCase(getLocationsUseCaseImpl: GetLocationsUseCaseImpl): GetLocationsUseCase


    @Binds
    @ViewModelScoped
    abstract fun bindGetCharactersByIdUseCase(getCharactersByIdUseCaseImpl: GetCharactersByIdUseCaseImpl): GetCharactersByIdUseCase
}