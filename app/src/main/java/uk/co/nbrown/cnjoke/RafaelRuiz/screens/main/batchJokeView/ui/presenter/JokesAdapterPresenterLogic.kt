package uk.co.nbrown.cnjoke.RafaelRuiz.screens.main.batchJokeView.ui.presenter

import android.content.Context
import android.view.LayoutInflater
import uk.co.nbrown.cnjoke.RafaelRuiz.R
import uk.co.nbrown.cnjoke.RafaelRuiz.screens.main.batchJokeView.ui.view.JokesAdapterView

/**
 * Created by rafaelruizmunoz on 08/11/2017.
 */

class JokesAdapterPresenterLogic constructor(private var context: Context) : JokesAdapterPresenter {
    override fun onCreateViewHolder(viewType: Int): JokesAdapterView.JokeViewHolder? {
        return JokesAdapterView.JokeViewHolder(LayoutInflater.from(context).inflate(R.layout.view_single_joke, null, false))
    }

    override fun onBindViewHolder(holder: JokesAdapterView.JokeViewHolder, position: Int, jokes: ArrayList<String>?) {
        holder.setJoke(
                if (isLoadingHolder(position, jokes)) {
                    context.getString(R.string.Loading)
                } else {
                    jokes!![position]
                }
        )
    }

    fun isLoadingHolder(position: Int, jokes: ArrayList<String>?): Boolean {
        return (jokes == null || position >= jokes.size - 1)
    }

}
