package uk.co.nbrown.cnjoke.RafaelRuiz.screens.main.batchJokeView.ui.presenter

import android.support.v7.widget.RecyclerView

/**
 * Created by rafaelruizmunoz on 08/11/2017.
 */

interface BatchJokeLogicPresenter {

    fun loadBatches(recyclerView: RecyclerView)
    fun configureRecyclerView(recyclerView: RecyclerView)
}
