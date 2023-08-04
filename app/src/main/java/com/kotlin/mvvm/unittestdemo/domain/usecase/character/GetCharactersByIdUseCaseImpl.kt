package com.kotlin.mvvm.unittestdemo.domain.usecase.character


import com.kotlin.mvvm.unittestdemo.data.NetworkResponseState
import com.kotlin.mvvm.unittestdemo.domain.entity.CharacterEntity
import com.kotlin.mvvm.unittestdemo.domain.repository.RickAndMortyRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCharactersByIdUseCaseImpl @Inject constructor(
    private val repository: RickAndMortyRepository
) : GetCharactersByIdUseCase {
    override suspend fun invoke(characterIds: List<String>): Flow<NetworkResponseState<List<CharacterEntity>>> = repository.getCharactersById(characterIds)
}