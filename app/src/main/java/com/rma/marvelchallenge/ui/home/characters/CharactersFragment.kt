package com.rma.marvelchallenge.ui.home.characters

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rma.marvelchallenge.R
import com.rma.marvelchallenge.core.exception.Failure
import com.rma.marvelchallenge.core.platform.PaginationListener
import com.rma.marvelchallenge.core.platform.ProgressDialog
import com.rma.marvelchallenge.data.ApiClient
import com.rma.marvelchallenge.domain.Character
import com.rma.marvelchallenge.features.character.CharacterRepository
import com.rma.marvelchallenge.features.character.GetCharacters
import com.rma.marvelchallenge.ui.character.CharacterDetailActivity

class CharactersFragment : Fragment(), CharactersMVP.View {

    private lateinit var characterAdapter: CharacterItemAdapter
    private lateinit var characterCollection: RecyclerView
    private lateinit var paginationListener: PaginationListener
    private lateinit var progressBar: ProgressBar
    private lateinit var progressDialog: ProgressDialog
    private var isLastPage = false
    private var isLoading = false
    private var currentPage = 0

    private lateinit var presenter: CharactersMVP.Presenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_characters, container, false)

        progressBar = root.findViewById(R.id.progressBar)

        // Configure character item adapter
        val linearLayoutManager = LinearLayoutManager(requireContext())
        characterCollection = root.findViewById(R.id.charactersCollection)
        characterCollection.layoutManager = linearLayoutManager
        characterAdapter = CharacterItemAdapter(mutableListOf(), requireContext())
        characterCollection.adapter = characterAdapter

        characterAdapter.addListener(object : CharacterItemAdapter.Callback {
            override fun onCharacterTapped(character: Character) {
                val intent = Intent(requireContext(), CharacterDetailActivity::class.java)
                intent.putExtra(CharacterDetailActivity.EXTRA_CHARACTER_ID, character.id)
                startActivity(intent)
            }
        })

        paginationListener = object : PaginationListener(linearLayoutManager) {
            override fun loadMoreItems() {
                currentPage++
                presenter.fetchCharacters(currentPage, paginationListener.itemsPerPage)
            }

            override fun isLastPage(): Boolean {
                return isLastPage
            }

            override fun isLoading(): Boolean {
                return isLoading
            }

        }
        characterCollection.addOnScrollListener(paginationListener)

        // TODO: use DI
        progressDialog = ProgressDialog(requireContext())
        presenter = CharactersPresenter(this,
            GetCharacters(
                CharacterRepository(
                    ApiClient.getApiService()
                )
            )
        )
        presenter.fetchCharacters(currentPage, paginationListener.itemsPerPage)
        return root
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume(this)
        progressDialog.attach(requireContext())
    }

    override fun onDestroy() {
        presenter.onDestroy()
        progressDialog.attach(null)
        super.onDestroy()
    }

    override fun showProgress() {
        if (currentPage == 0) {
            progressDialog.show()
        }else{
            isLoading = true
            progressBar.visibility = View.VISIBLE
        }
    }

    override fun hideProgress() {
        if (currentPage == 0) {
            progressDialog.dismiss()
        }else{
            isLoading = false
            progressBar.visibility = View.GONE
        }
    }

    override fun showCharacters(items: List<Character>) {
        if (items.isEmpty()) isLastPage = true
        characterAdapter.addItems(items)
    }

    override fun showFailure(failure: Failure) {
        Toast.makeText(requireContext(), "Error al cargar personajes", Toast.LENGTH_SHORT)
            .show()
    }
}