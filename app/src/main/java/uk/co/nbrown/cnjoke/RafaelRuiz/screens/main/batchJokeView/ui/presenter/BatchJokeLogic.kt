package uk.co.nbrown.cnjoke.RafaelRuiz.screens.main.batchJokeView.ui.presenter

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.LinearLayoutManager.VERTICAL
import android.support.v7.widget.RecyclerView
import uk.co.nbrown.cnjoke.RafaelRuiz.screens.main.activity.domain.JokeUseCase
import uk.co.nbrown.cnjoke.RafaelRuiz.screens.main.activity.ui.presenter.MainActivityPresenter
import uk.co.nbrown.cnjoke.RafaelRuiz.screens.main.batchJokeView.ui.view.JokesAdapterView


/**
 * Created by rafaelruizmunoz on 08/11/2017.
 */

class BatchJokeLogic constructor(private val jokeUseCase: JokeUseCase, private val mainActivityPresenter: MainActivityPresenter) : BatchJokeLogicPresenter {
    override fun loadBatches(recyclerView: RecyclerView) {
        mainActivityPresenter.setLoading(true)
        jokeUseCase.getBatchJokes(mainActivityPresenter.isNoExplicit())
                .subscribe(
                        { jokes ->
                            mainActivityPresenter.setLoading(false)
                            setUpJokes(recyclerView, jokes)
                        },
                        {
                            mainActivityPresenter.setLoading(false)
                            mainActivityPresenter.onError()
                        }
                )
    }

    fun setUpJokes(recyclerView: RecyclerView, jokes: ArrayList<String>) {
        if (recyclerView.layoutManager == null) {
            recyclerView.layoutManager = LinearLayoutManager(
                    mainActivityPresenter.getContext(),
                    VERTICAL,
                    false)
        }

        if (recyclerView.adapter == null) {
            recyclerView.adapter = JokesAdapterView(
                    JokesAdapterPresenterLogic(mainActivityPresenter.getContext())
            )
        }

        (recyclerView.adapter as JokesAdapterView).addData(jokes, mainActivityPresenter)
    }

    override fun configureRecyclerView(recyclerView: RecyclerView) {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (!recyclerView!!.canScrollVertically(1)) {
                    loadBatches(recyclerView)
                }
            }
        })
    }
}
