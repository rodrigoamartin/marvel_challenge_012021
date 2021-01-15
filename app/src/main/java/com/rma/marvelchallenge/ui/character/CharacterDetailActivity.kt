package com.rma.marvelchallenge.ui.character

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.rma.marvelchallenge.R
import com.rma.marvelchallenge.core.exception.Failure
import com.rma.marvelchallenge.core.platform.IconHelper
import com.rma.marvelchallenge.core.platform.ProgressDialog
import com.rma.marvelchallenge.data.ApiClient
import com.rma.marvelchallenge.domain.Character
import com.rma.marvelchallenge.domain.Comic
import com.rma.marvelchallenge.features.character.CharacterRepository
import com.rma.marvelchallenge.features.character.GetCharacter
import kotlinx.android.synthetic.main.activity_character_detail.*

class CharacterDetailActivity : AppCompatActivity(), CharacterDetailMVP.View {

    private var characterId: String? = null
    private lateinit var progressDialog: ProgressDialog
    private lateinit var presenter: CharacterDetailMVP.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_detail)

        closeActionBar.setOnClickListener { finish() }

        characterId = intent.extras?.getString(EXTRA_CHARACTER_ID, "") ?: ""

        progressDialog = ProgressDialog(this)
        presenter = CharacterDetailPresenter(this,
            GetCharacter(
                CharacterRepository(
                    ApiClient.getApiService()
                )
            )
        )
        presenter.fetchCharacter()
    }

    override fun onResume() {
        super.onResume()
        progressDialog.attach(this)
        presenter.onResume(this)
    }

    override fun onDestroy() {
        progressDialog.attach(null)
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun getCharacterId(): String {
        return characterId ?: ""
    }

    override fun showProgress() {
        progressDialog.show()
    }

    override fun hideProgress() {
        progressDialog.dismiss()
    }

    override fun showCharacter(character: Character) {
        characterNameLabel.text = character.name
        characterDescription.text = character.description

        val iconHelper = IconHelper(this)
        iconHelper.draw(character.iconUrl, avatarImage)

        comicItemsContainer.removeAllViews()
        for (comic in character.comics){
            renderComic(comic)
        }
    }

    private fun renderComic(comic: Comic) {
        val view = View.inflate(this, R.layout.item_character_comic, null)
        val nameLabel = view.findViewById<TextView>(R.id.characterComicNameLabel)
        nameLabel.text = comic.name
        if (comic.year.isNotEmpty()){
            val yearLabel = view.findViewById<TextView>(R.id.characterComicYearLabel)
            yearLabel.text = comic.year
        }
        comicItemsContainer.addView(view)
    }

    override fun showFailure(failure: Failure) {
        Toast.makeText(
            this,
            if (failure.hasMessage) failure.message else "Error al obtener detalles del personaje",
            Toast.LENGTH_LONG
        ).show()
    }

    companion object{
        const val EXTRA_CHARACTER_ID = "characterId"
    }
}