package uk.co.nbrown.cnjoke.RafaelRuiz.screens.main.activity.ui.presenter

import android.os.Bundle
import android.widget.RelativeLayout
import uk.co.nbrown.cnjoke.RafaelRuiz.screens.main.activity.domain.JokeUseCase
import uk.co.nbrown.cnjoke.RafaelRuiz.screens.main.batchJokeView.ui.view.BatchJokeView
import uk.co.nbrown.cnjoke.RafaelRuiz.screens.main.jokeView.ui.view.JokeView
import uk.co.nbrown.cnjoke.RafaelRuiz.screens.main.jokeView.ui.view.OnAnother
import uk.co.nbrown.cnjoke.RafaelRuiz.screens.main.jokeView.ui.view.TextInputJokeView

/**
 * Created by rafaelruizmunoz on 08/11/2017.
 */

interface MainActivityLogicInterface {
    /**
     * Random Joke
     */

    fun getRandomJokeView(joke: String, onAnother: OnAnother): JokeView

    fun onRandomJoke(mainContent: RelativeLayout, jokeUseCase: JokeUseCase, noExplicit: Boolean)

    fun onRandomGivenJoke(mainContent: RelativeLayout, jokeUseCase: JokeUseCase, joke: String)

    /**
     * Text Input Joke
     */
    fun getTextInputJokeView(jokeUseCase: JokeUseCase): TextInputJokeView

    fun onTextInput(mainContent: RelativeLayout, jokeUseCase: JokeUseCase)

    fun onGivenTextInput(mainContent: RelativeLayout, jokeUseCase: JokeUseCase, textInput: String, joke: String)

    /**
     * Batch jokes
     */

    fun getBatchJokeView(initList: ArrayList<String>?): BatchJokeView

    fun addViewWithBatchRandomJokes(mainContent: RelativeLayout, initList : ArrayList<String>?) : BatchJokeView

    fun addViewWithGivenBatchRandomJokes(mainContent: RelativeLayout, initList: ArrayList<String>)


    /**
     * Functions to recover data from rotations or other situations
     */

    fun onCreate(savedInstanceState: Bundle?)

    fun onSaveInstanceState(outState: Bundle?, currentCategory: String?, last: Any?, lastTextInput: String?)

}
