package com.kotlin.mvvm.unittestdemo.di

import com.kotlin.mvvm.unittestdemo.data.source.remote.RemoteDataSource
import com.kotlin.mvvm.unittestdemo.data.source.remote.RemoteDataSourceImpl
import com.kotlin.mvvm.unittestdemo.data.source.local.LocalDataSource
import com.kotlin.mvvm.unittestdemo.data.source.local.LocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class DataSourceModule {
    @Binds
    @ViewModelScoped
    abstract fun bindRemoteDataSource(remoteDataSourceImpl: RemoteDataSourceImpl): RemoteDataSource

    @Binds
    @ViewModelScoped
    abstract fun bindLocalDataSource(localDataSourceImpl: LocalDataSourceImpl):LocalDataSource
}