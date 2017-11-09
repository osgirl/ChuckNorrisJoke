package uk.co.nbrown.cnjoke.RafaelRuiz.screens.main.textInput.ui.presenter

import io.reactivex.functions.Consumer

/**
 * Created by rafaelruizmunoz on 08/11/2017.
 */

interface OnSearchPresenter {
    fun search(firstName: String,
               lastName: String,
               noExplicit: Boolean,
               onSuccess: Consumer<String>,
               onError: Consumer<Throwable>)

    fun isValidName(name: String) : Boolean

    fun getFirstName(textInput: String): String

    fun getLastName(textInput: String): String
}
