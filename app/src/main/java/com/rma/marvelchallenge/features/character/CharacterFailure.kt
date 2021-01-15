package com.rma.marvelchallenge.features.character

import com.rma.marvelchallenge.core.exception.Failure

sealed class CharacterFailure : Failure.FeatureFailure(){
    object CharacterNotFound: CharacterFailure()
    object EmptyCharacterId: CharacterFailure()
}