package uk.co.nbrown.cnjoke.RafaelRuiz.screens.main.jokeView.ui.view

import android.content.Context
import android.support.v7.widget.AppCompatButton
import android.support.v7.widget.AppCompatEditText
import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.CardView
import android.view.LayoutInflater
import android.widget.RelativeLayout
import butterknife.BindView
import butterknife.ButterKnife
import uk.co.nbrown.cnjoke.RafaelRuiz.R
import uk.co.nbrown.cnjoke.RafaelRuiz.helpers.ViewHelpers
import uk.co.nbrown.cnjoke.RafaelRuiz.screens.main.activity.ui.presenter.MainActivityPresenter
import uk.co.nbrown.cnjoke.RafaelRuiz.screens.main.jokeView.ui.presenter.TextInputJokeViewPresenter
import uk.co.nbrown.cnjoke.RafaelRuiz.screens.main.textInput.ui.presenter.OnSearchPresenter

/**
 * Created by rafaelruizmunoz on 08/11/2017.
 */

class TextInputJokeView(context: Context, private var onSearchPresenter: OnSearchPresenter, private var mainActivityPresenter: MainActivityPresenter) : RelativeLayout(context), TextInputJokeViewPresenter {

    @BindView(R.id.searchET)
    lateinit var searchET: AppCompatEditText

    @BindView(R.id.searchButton)
    lateinit var searchButton: AppCompatButton

    @BindView(R.id.jokeTV)
    lateinit var jokeTV: AppCompatTextView

    @BindView(R.id.cardView)
    lateinit var cardView: CardView

    init {
        initView()
        setClickListeners()
    }

    override fun initView() {
        LayoutInflater.from(context).inflate(R.layout.view_text_input_joke, this)
        ButterKnife.bind(this)
    }

    override fun displayJoke(joke: String) {
        if (joke.isEmpty()) {
            return;
        }

        if (cardView.visibility != VISIBLE) {
            cardView.visibility = VISIBLE
        }
        jokeTV.text = joke
    }

    override fun setClickListeners() {
        searchButton.setOnClickListener({
            search()
        })
    }

    private fun search() {
        val mainCharacterString = searchET.text.toString()
        if (mainCharacterString.isEmpty()) {
            mainActivityPresenter.onNoNameInput()
            return
        }

        onSearchPresenter.search(mainCharacterString, mainActivityPresenter.isNoExplicit())
        ViewHelpers.closeKeyboard(this)
    }

    fun setInET(textInput: String) {
        searchET.setText(textInput)
    }


}
