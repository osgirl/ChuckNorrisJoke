package uk.co.nbrown.cnjoke.RafaelRuiz.screens.main.jokeView.ui.view

import android.content.Context
import android.support.v7.widget.AppCompatButton
import android.support.v7.widget.AppCompatTextView
import android.view.LayoutInflater
import android.widget.RelativeLayout
import butterknife.BindView
import butterknife.ButterKnife
import uk.co.nbrown.cnjoke.RafaelRuiz.R
import uk.co.nbrown.cnjoke.RafaelRuiz.screens.main.jokeView.ui.presenter.TextInputJokeViewPresenter

/**
 * Created by rafaelruizmunoz on 08/11/2017.
 */

class JokeView(context: Context, joke: String, var onAnother: OnAnother) : RelativeLayout(context), TextInputJokeViewPresenter {
    @BindView(R.id.jokeTV)
    lateinit var jokeTV: AppCompatTextView

    @BindView(R.id.anotherOneButton)
    lateinit var anotherOneButton: AppCompatButton

    init {
        initView()
        displayJoke(joke)
        setClickListeners()
    }

    override fun initView() {
        LayoutInflater.from(context).inflate(R.layout.view_random_joke, this)
        ButterKnife.bind(this)
    }

    override fun displayJoke(joke: String) {
        jokeTV.text = joke
    }

    fun setJoke(joke: String) {
        displayJoke(joke)
    }

    override fun setClickListeners() {
        anotherOneButton.setOnClickListener({ onAnother.anotherOne(anotherOneButton) })
    }


}
