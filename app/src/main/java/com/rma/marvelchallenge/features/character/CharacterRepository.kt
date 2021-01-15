package com.rma.marvelchallenge.features.character

import android.util.Log
import com.rma.marvelchallenge.core.exception.Failure
import com.rma.marvelchallenge.core.functional.Either
import com.rma.marvelchallenge.data.ApiService
import com.rma.marvelchallenge.data.response.character.toDomain
import com.rma.marvelchallenge.domain.Character

class CharacterRepository (
    private val apiService: ApiService
): ICharacterRepository {

    override fun getCharacters(offset: Int, limit: Int): Either<Failure, List<Character>> {
        try {
            val result = apiService.getCharacters(
                offset, limit
            ).execute()
            if (result.isSuccessful){
                if (result.body()?.code == 200){
                    val data = result.body()?.data!!
                    val characterList = mutableListOf<Character>()
                    for (characterResponse in data.results){
                        val character = characterResponse.toDomain()
                        characterList.add(character)
                    }
                    return Either.Right(characterList)
                }
            }
            return Either.Left(Failure.ServerError)
        }catch (e: Throwable){
            Log.e(TAG, "getCharacters: ${e.message ?: ""}", e)
            return Either.Left(Failure.ServerError)
        }
    }

    override fun getCharacter(id: String): Either<Failure, Character> {
        try {
            val result = apiService.getCharacter(id).execute()
            if (result.isSuccessful){
                if (result.body()?.code == 200){
                    val data = result.body()?.data!!
                    if (data.results.isEmpty()) return Either.Left(CharacterFailure.CharacterNotFound)
                    val character = data.results[0].toDomain()
                    return Either.Right(character)
                }
            }
            return Either.Left(Failure.ServerError)
        }catch (e: Throwable){
            Log.e(TAG, "getCharacters: ${e.message ?: ""}", e)
            return Either.Left(Failure.ServerError)
        }
    }

    companion object{
        var TAG : String = CharacterRepository::class.java.name
    }
}