package uk.co.nbrown.cnjoke.RafaelRuiz.screens.main.jokeView.ui.presenter

/**
 * Created by rafaelruizmunoz on 08/11/2017.
 */

interface TextInputJokeViewPresenter {

    fun initView()

    fun displayJoke(joke: String)

    fun setClickListeners()
}
