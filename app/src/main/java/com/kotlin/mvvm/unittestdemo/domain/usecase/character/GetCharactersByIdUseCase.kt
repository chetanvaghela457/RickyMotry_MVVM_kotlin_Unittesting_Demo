package com.kotlin.mvvm.unittestdemo.domain.usecase.character

import com.kotlin.mvvm.unittestdemo.data.NetworkResponseState
import com.kotlin.mvvm.unittestdemo.domain.entity.CharacterEntity
import kotlinx.coroutines.flow.Flow

interface GetCharactersByIdUseCase {
    suspend  operator fun invoke(characterIds:List<String>): Flow<NetworkResponseState<List<CharacterEntity>>>
}
