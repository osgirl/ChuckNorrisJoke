package uk.co.nbrown.cnjoke.RafaelRuiz.screens.main.textInput.ui.presenter

import io.reactivex.functions.Consumer
import uk.co.nbrown.cnjoke.RafaelRuiz.screens.main.activity.domain.JokeUseCase

/**
 * Created by rafaelruizmunoz on 08/11/2017.
 */

class OnSearchPresenterLogic(private val jokeUseCase: JokeUseCase) : OnSearchPresenter {

    override fun search(mainCharacter: String, noExplicit: Boolean) {
        jokeUseCase.getJokeWithMainCharacter(mainCharacter, noExplicit)
                .subscribe(
                        Consumer {

                        },
                        Consumer {

                        }
                )
    }
}
