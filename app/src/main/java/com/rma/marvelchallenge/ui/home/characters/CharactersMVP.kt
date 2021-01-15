package com.rma.marvelchallenge.ui.home.characters

import com.rma.marvelchallenge.core.exception.Failure
import com.rma.marvelchallenge.domain.Character

interface CharactersMVP {

    interface View{
        fun showProgress()
        fun hideProgress()
        fun showCharacters(items: List<Character>)
        fun showFailure(failure: Failure)
    }

    interface Presenter{
        fun onResume(view: View)
        fun onDestroy()
        fun fetchCharacters(page: Int, limit: Int)
    }
}