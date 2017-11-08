package uk.co.nbrown.cnjoke.RafaelRuiz.screens.main.batchJokeView.ui.view

import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import butterknife.BindView
import butterknife.ButterKnife
import uk.co.nbrown.cnjoke.RafaelRuiz.R
import uk.co.nbrown.cnjoke.RafaelRuiz.screens.main.activity.ui.presenter.MainActivityPresenter
import uk.co.nbrown.cnjoke.RafaelRuiz.screens.main.batchJokeView.ui.presenter.JokesAdapterPresenter
import java.util.*


/**
 * Created by rafaelruizmunoz on 08/11/2017.
 */

class JokesAdapterView(private val jokesAdapterPresenter: JokesAdapterPresenter) : RecyclerView.Adapter<JokesAdapterView.JokeViewHolder>() {

    private var jokes: ArrayList<String>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokeViewHolder? {
        return jokesAdapterPresenter.onCreateViewHolder(viewType)
    }

    override fun onBindViewHolder(holder: JokeViewHolder, position: Int) {
        jokesAdapterPresenter.onBindViewHolder(holder, position, jokes)
    }

    override fun getItemCount(): Int {
        return if (jokes == null || jokes!!.size == 0) 0 else jokes!!.size
    }

    fun addData(data : ArrayList<String>, mainActivityPresenter: MainActivityPresenter) {
        if (jokes == null) {
            jokes = ArrayList()
        }

        jokes!!.addAll(data)
        mainActivityPresenter.setLastLoaded(jokes)
        notifyDataSetChanged()
    }

    class JokeViewHolder constructor(var view: View) : RecyclerView.ViewHolder(view) {

        @BindView(R.id.jokeTV)
        lateinit var jokeTV: AppCompatTextView

        init {
            ButterKnife.bind(this, view)
        }

        fun setJoke(joke: String) {
            jokeTV.text = joke
        }
    }
}
