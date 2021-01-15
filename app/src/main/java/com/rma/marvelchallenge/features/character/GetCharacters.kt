package com.rma.marvelchallenge.features.character

import com.rma.marvelchallenge.core.exception.Failure
import com.rma.marvelchallenge.core.functional.Either
import com.rma.marvelchallenge.core.interactor.UseCase
import com.rma.marvelchallenge.domain.Character

class GetCharacters(
    private val characterRepository: ICharacterRepository
): UseCase<List<Character>, GetCharacters.Params>(){

    data class Params(val page: Int = 0, val limit: Int = 0)

    override suspend fun run(params: Params): Either<Failure, List<Character>> {

        val offset: Int = params.page * params.limit

        return characterRepository.getCharacters(offset, params.limit)
    }

}