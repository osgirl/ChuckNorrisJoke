package uk.co.nbrown.cnjoke.RafaelRuiz.screens.main.activity.ui.presenter

import android.widget.RelativeLayout
import uk.co.nbrown.cnjoke.RafaelRuiz.screens.main.activity.domain.JokeUseCase
import uk.co.nbrown.cnjoke.RafaelRuiz.screens.main.jokeView.ui.view.JokeView
import uk.co.nbrown.cnjoke.RafaelRuiz.screens.main.jokeView.ui.view.OnAnother

/**
 * Created by rafaelruizmunoz on 08/11/2017.
 */

interface MainActivityLogicInterface {

    fun getRandomJokeView(joke: String, onAnother: OnAnother): JokeView

    fun onRandomJoke(mainContent: RelativeLayout, jokeUseCase: JokeUseCase, noExplicit: Boolean)
}
