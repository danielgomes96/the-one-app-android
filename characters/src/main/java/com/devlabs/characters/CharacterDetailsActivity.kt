package com.devlabs.characters

import android.os.Bundle
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.devlabs.core.Constants.KEY_CHARACTER_DETAILS
import com.devlabs.core.extension.bind
import com.devlabs.core.extension.gone
import com.devlabs.core.extension.visible
import com.devlabs.domain.entity.Character
import com.devlabs.domain.entity.Quote
import com.devlabs.domain.entity.ResultWrapper
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharacterDetailsActivity : AppCompatActivity() {

    private val quotesAdapter: QuotesAdapter by lazy {
        QuotesAdapter()
    }

    private val character: Character by lazy {
        intent.getSerializableExtra(KEY_CHARACTER_DETAILS) as Character
    }

    private val viewModel by viewModel<CharacterDetailsViewModel>()

    private val tvCharacterGender: TextView by bind(R.id.activity_character_details_tv_gender)
    private val tvCharacterBirth: TextView by bind(R.id.activity_character_details_tv_birth)
    private val progressQuotes: ProgressBar by bind(R.id.activity_character_details_progress_quotes)
    private val rvQuotesList: RecyclerView by bind(R.id.activity_character_details_quotes_list)
    private val emptyQuotesContainer: LinearLayout by bind(R.id.empty_characters_quote_container)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_details)
        setupActionBar()
        setupCharacterDetails()
        setupObserver()
        setupRecyclerView()
        viewModel.requestCharacterQuotes(character.id)
    }

    private fun setupRecyclerView() {
        rvQuotesList.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = quotesAdapter
        }
    }

    private fun setupObserver() {
        viewModel.quotesListLiveData.observe(this, ::handleResult)
    }

    private fun handleResult(result: ResultWrapper<List<Quote>>?) {
        when(result) {
            is ResultWrapper.Success -> {
                progressQuotes.gone()
                quotesAdapter.addItemsToList(result.value)
            }
            is ResultWrapper.GenericError -> {

            }
            ResultWrapper.Empty -> {
                progressQuotes.gone()
                emptyQuotesContainer.visible()
            }
            is ResultWrapper.InitialState -> {
                progressQuotes.visible()
            }
            ResultWrapper.Loading -> {
                progressQuotes.visible()
            }
        }
    }

    private fun setupCharacterDetails() {
        tvCharacterGender.text = character.gender
        tvCharacterBirth.text = character.age.ifEmpty {
            getString(R.string.empty_birth)
        }
    }

    private fun setupActionBar() {
        supportActionBar?.title = character.name
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}