package com.devlabs.characters

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.devlabs.core.Constants.KEY_CHARACTER_DETAILS
import com.devlabs.core.extension.bind
import com.devlabs.domain.entity.Character
import com.devlabs.domain.entity.ResultWrapper
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharactersListActivity : AppCompatActivity() {

    private val viewModel by viewModel<CharactersListViewModel>()
    private val charactersListAdapter: CharactersListAdapter by lazy {
        CharactersListAdapter { character ->
            openCharacterDetails(character)
        }
    }

    private val rvCharactersList: RecyclerView by bind(R.id.activity_characters_list_recycler)

    private val paginationScrollListener: PaginationScrollListener by lazy {
        object : PaginationScrollListener(rvCharactersList.layoutManager as LinearLayoutManager) {
            override fun loadMoreItems() {
                viewModel.requestCharacters()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_characters_list)
        setupActionBar()
        viewModel.requestCharacters()
        initObserver()
        setupRecyclerView()
    }

    private fun setupActionBar() {
        supportActionBar?.title = getString(R.string.title_characters_list)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setupRecyclerView() {
        rvCharactersList.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = charactersListAdapter
            addOnScrollListener(paginationScrollListener)
        }
    }

    private fun initObserver() {
        viewModel.charactersListLiveData.observe(this, ::handleResult)
    }

    private fun handleResult(result: ResultWrapper<List<Character>>) {
        when(result) {
            is ResultWrapper.Success -> {
                charactersListAdapter.addItemsToList(result.value)
            }
            is ResultWrapper.GenericError -> {
                viewModel.currentPage = viewModel.currentPage.dec()
            }
            ResultWrapper.Empty -> {

            }
            is ResultWrapper.InitialState -> {

            }
            ResultWrapper.Loading -> {

            }
        }
    }

    private fun openCharacterDetails(character: Character) {
        val intent = Intent(this, CharacterDetailsActivity::class.java)
        intent.putExtra(KEY_CHARACTER_DETAILS, character)
        startActivity(intent)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

}