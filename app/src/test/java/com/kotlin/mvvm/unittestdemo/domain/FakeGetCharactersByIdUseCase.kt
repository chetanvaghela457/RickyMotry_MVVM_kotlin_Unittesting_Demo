package com.kotlin.mvvm.unittestdemo.domain

import com.kotlin.mvvm.unittestdemo.apiException
import com.kotlin.mvvm.unittestdemo.characterList
import com.kotlin.mvvm.unittestdemo.data.NetworkResponseState
import com.kotlin.mvvm.unittestdemo.domain.entity.CharacterEntity
import com.kotlin.mvvm.unittestdemo.domain.usecase.character.GetCharactersByIdUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeGetCharactersByIdUseCase : GetCharactersByIdUseCase {

    private var showError = false
    fun updateShowError(showError:Boolean){
        this.showError = showError
    }
    override suspend fun invoke(characterIds: List<String>): Flow<NetworkResponseState<List<CharacterEntity>>> =
        flow {
            emit(NetworkResponseState.Loading)
            if (showError){
                emit(NetworkResponseState.Error(apiException))
            }else{
                emit(NetworkResponseState.Success(characterList))
            }
        }

}