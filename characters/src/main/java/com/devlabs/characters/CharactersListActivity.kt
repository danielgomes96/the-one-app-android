package com.devlabs.characters

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.devlabs.domain.entity.Character
import com.devlabs.domain.entity.ResultWrapper
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharactersListActivity : AppCompatActivity() {

    private val viewModel by viewModel<CharactersListViewModel>()
    private val charactersListAdapter: CharactersListAdapter by lazy {
        CharactersListAdapter {
            //openCharacterDetails()
        }
    }

    private val rvCharactersList: RecyclerView by lazy {
        findViewById(R.id.activity_characters_list_recycler)
    }

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

            }
            ResultWrapper.Empty -> {

            }
            is ResultWrapper.InitialState -> {

            }
            ResultWrapper.Loading -> {

            }
            ResultWrapper.NetworkError -> {

            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

}