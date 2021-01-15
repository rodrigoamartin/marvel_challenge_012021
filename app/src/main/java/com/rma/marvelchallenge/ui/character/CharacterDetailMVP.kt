package com.rma.marvelchallenge.ui.character

import com.rma.marvelchallenge.core.exception.Failure
import com.rma.marvelchallenge.domain.Character

interface CharacterDetailMVP {
    interface View{
        fun getCharacterId(): String
        fun showProgress()
        fun hideProgress()
        fun showCharacter(character: Character)
        fun showFailure(failure: Failure)
    }
    interface Presenter{
        fun onResume(view: View)
        fun onDestroy()
        fun fetchCharacter()
    }
}