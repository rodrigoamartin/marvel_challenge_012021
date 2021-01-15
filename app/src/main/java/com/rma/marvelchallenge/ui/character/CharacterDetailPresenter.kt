package com.rma.marvelchallenge.ui.character

import com.rma.marvelchallenge.core.functional.onFailure
import com.rma.marvelchallenge.core.functional.onSuccess
import com.rma.marvelchallenge.features.character.GetCharacter

class CharacterDetailPresenter(
    private var view: CharacterDetailMVP.View? = null,
    private val getCharacter: GetCharacter
) : CharacterDetailMVP.Presenter{

    override fun onResume(view: CharacterDetailMVP.View) {
        this.view = view
    }

    override fun onDestroy() {
        this.view = null
    }

    override fun fetchCharacter() {
        view?.showProgress()
        getCharacter(GetCharacter.Params(view?.getCharacterId() ?: "")){ result ->
            result.onSuccess {
                view?.hideProgress()
                view?.showCharacter(it)
            }
            result.onFailure {
                view?.hideProgress()
                view?.showFailure(it)
            }
        }
    }

}