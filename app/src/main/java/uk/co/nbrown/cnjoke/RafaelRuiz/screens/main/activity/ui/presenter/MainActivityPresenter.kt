package uk.co.nbrown.cnjoke.RafaelRuiz.screens.main.activity.ui.presenter

import android.content.Context

/**
 * Created by rafaelruizmunoz on 08/11/2017.
 */

interface MainActivityPresenter {

    fun onRandomJoke()

    fun onTextInput()

    fun onNeverEndingJoke()

    fun setLoading(loading: Boolean)

    fun setTitle(title: String)

    fun setOnClickListeners()

    fun getContext(): Context

    fun closeFaMenu()

    fun isNoExplicit() : Boolean
}
