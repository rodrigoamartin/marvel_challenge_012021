package com.rma.marvelchallenge.features.character

import com.rma.marvelchallenge.core.exception.Failure
import com.rma.marvelchallenge.core.functional.Either
import com.rma.marvelchallenge.domain.Character

interface ICharacterRepository {
    fun getCharacters(offset: Int = 0, limit: Int = 0): Either<Failure, List<Character>>
    fun getCharacter(id: String): Either<Failure, Character>
}