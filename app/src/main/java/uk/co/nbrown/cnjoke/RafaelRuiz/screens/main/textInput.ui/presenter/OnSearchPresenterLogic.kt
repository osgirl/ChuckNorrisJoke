package uk.co.nbrown.cnjoke.RafaelRuiz.screens.main.textInput.ui.presenter

import io.reactivex.functions.Consumer
import uk.co.nbrown.cnjoke.RafaelRuiz.screens.main.activity.domain.JokeUseCase

/**
 * Created by rafaelruizmunoz on 08/11/2017.
 */

class OnSearchPresenterLogic(private val jokeUseCase: JokeUseCase) : OnSearchPresenter {
    override fun getLastName(textInput: String): String {
        return textInput.split(" ")[1]
    }

    override fun getFirstName(textInput: String): String {
        return textInput.split(" ")[0]
    }

    override fun isValidName(name: String): Boolean {
        return !name.isEmpty() && name.split(" ").size == 2 && !getFirstName(name).isEmpty() && !getLastName(name).isEmpty()
    }

    override fun search(firstName: String,
                        lastName: String,
                        noExplicit: Boolean,
                        onSuccess: Consumer<String>,
                        onError: Consumer<Throwable>) {
        jokeUseCase.getJokeWithMainCharacter(firstName, lastName, noExplicit)
                .subscribe(onSuccess, onError);
    }
}
