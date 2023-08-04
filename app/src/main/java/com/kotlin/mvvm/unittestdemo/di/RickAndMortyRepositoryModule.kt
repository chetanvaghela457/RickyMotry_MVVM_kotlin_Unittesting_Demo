package com.kotlin.mvvm.unittestdemo.di

import com.kotlin.mvvm.unittestdemo.data.repository.RickAndMortyRepositoryImpl
import com.kotlin.mvvm.unittestdemo.domain.repository.RickAndMortyRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class RickAndMortyRepositoryModule {
    @Binds
    @ViewModelScoped
    abstract fun bindRickAndMortyRepository(rickAndMortyRepositoryImpl: RickAndMortyRepositoryImpl):RickAndMortyRepository
}