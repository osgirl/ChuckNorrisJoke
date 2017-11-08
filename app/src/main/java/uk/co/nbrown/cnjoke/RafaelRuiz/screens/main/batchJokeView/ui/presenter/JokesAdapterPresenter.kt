package uk.co.nbrown.cnjoke.RafaelRuiz.screens.main.batchJokeView.ui.presenter

import uk.co.nbrown.cnjoke.RafaelRuiz.screens.main.batchJokeView.ui.view.JokesAdapterView

/**
 * Created by rafaelruizmunoz on 08/11/2017.
 */

interface JokesAdapterPresenter {

    fun onCreateViewHolder(viewType: Int): JokesAdapterView.JokeViewHolder?

    fun onBindViewHolder(holder: JokesAdapterView.JokeViewHolder, position: Int, jokes: ArrayList<String>?)
}