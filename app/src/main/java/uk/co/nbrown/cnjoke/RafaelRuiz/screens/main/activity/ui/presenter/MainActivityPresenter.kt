package uk.co.nbrown.cnjoke.RafaelRuiz.screens.main.activity.ui.presenter

import android.content.Context

/**
 * Created by rafaelruizmunoz on 08/11/2017.
 */

interface MainActivityPresenter {

    fun setGivenRandomJoke(joke: String)

    fun onRandomJoke()

    fun setGivenTextInputJoke(textInputText: String, joke: String)

    fun onTextInput()

    fun setGivenNeverEndingJokes(list: ArrayList<String>)

    fun onNeverEndingJoke()

    fun setLoading(loading: Boolean)

    fun setTitle(title: String?)

    fun getContext(): Context

    fun closeFaMenu()

    fun isNoExplicit(): Boolean

    fun configureFaMenu()

    fun onError()

    fun onNoNameInput()

    fun setLastLoaded(any: Any?)

    fun setLastTextInputLoaded(lastTI: String)

    fun setCategory(category: String?)
}
