package com.rma.marvelchallenge.features.character

import com.rma.marvelchallenge.core.exception.Failure
import com.rma.marvelchallenge.core.functional.Either
import com.rma.marvelchallenge.core.interactor.UseCase
import com.rma.marvelchallenge.domain.Character

class GetCharacter(
    private val characterRepository: ICharacterRepository
): UseCase<Character, GetCharacter.Params>(){

    data class Params(val id: String = "")

    override suspend fun run(params: Params): Either<Failure, Character> {
        when {
            params.id.isEmpty() -> return Either.Left(CharacterFailure.EmptyCharacterId)
        }
        return characterRepository.getCharacter(params.id)
    }
}