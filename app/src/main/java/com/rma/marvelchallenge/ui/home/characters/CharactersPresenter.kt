package com.rma.marvelchallenge.ui.home.characters

import com.rma.marvelchallenge.core.functional.onFailure
import com.rma.marvelchallenge.core.functional.onSuccess
import com.rma.marvelchallenge.features.character.GetCharacters

class CharactersPresenter(
    private var view: CharactersMVP.View? = null,
    private val getCharacters: GetCharacters
): CharactersMVP.Presenter{

    override fun onResume(view: CharactersMVP.View) {
        this.view = view
    }

    override fun onDestroy() {
        view = null
    }

    override fun fetchCharacters(page: Int, limit: Int) {
        view?.showProgress()
        getCharacters(GetCharacters.Params(page, limit)){ result ->
            result.onSuccess {
                view?.hideProgress()
                view?.showCharacters(it)
            }
            result.onFailure {
                view?.hideProgress()
                view?.showFailure(it)
            }
        }
    }

}