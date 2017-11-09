package uk.co.nbrown.cnjoke.RafaelRuiz.screens.main.activity.ui.presenter

import android.os.Bundle
import android.view.View
import android.widget.RelativeLayout
import uk.co.nbrown.cnjoke.RafaelRuiz.R
import uk.co.nbrown.cnjoke.RafaelRuiz.screens.main.activity.domain.JokeUseCase
import uk.co.nbrown.cnjoke.RafaelRuiz.screens.main.batchJokeView.ui.view.BatchJokeView
import uk.co.nbrown.cnjoke.RafaelRuiz.screens.main.jokeView.ui.view.JokeView
import uk.co.nbrown.cnjoke.RafaelRuiz.screens.main.jokeView.ui.view.OnAnother
import uk.co.nbrown.cnjoke.RafaelRuiz.screens.main.jokeView.ui.view.TextInputJokeView
import uk.co.nbrown.cnjoke.RafaelRuiz.screens.main.textInput.ui.presenter.OnSearchPresenter

/**
 * Created by rafaelruizmunoz on 08/11/2017.
 */

class MainActivityLogic constructor(private var mainActivityPresenter: MainActivityPresenter) : MainActivityLogicInterface {

    /**
     * Random Joke stuff
     */
    override fun getRandomJokeView(joke: String, onAnother: OnAnother): JokeView {
        return JokeView(mainActivityPresenter.getContext(), joke, onAnother)
    }

    private fun addViewWithRandomJoke(mainContent: RelativeLayout, joke: String, onAnother: OnAnother) {
        mainContent.removeAllViews()
        mainContent.addView(
                getRandomJokeView(joke, onAnother)
        )
    }

    override fun onRandomJoke(mainContent: RelativeLayout, jokeUseCase: JokeUseCase, noExplicit: Boolean) {
        mainActivityPresenter.setLastLoaded(null)
        mainActivityPresenter.setLoading(true)

        jokeUseCase.getRandomJoke(noExplicit)
                .subscribe(
                        { joke ->
                            mainActivityPresenter.setLoading(false)
                            mainActivityPresenter.setLastLoaded(joke)
                            addViewWithRandomJoke(
                                    mainContent,
                                    joke,
                                    object : OnAnother {
                                        override fun anotherOne(view: View) {
                                            onRandomJoke(mainContent, jokeUseCase, mainActivityPresenter.isNoExplicit())
                                        }
                                    }
                            )
                            mainActivityPresenter.setTitle(mainActivityPresenter.getContext().getString(R.string.random_joke))
                        },
                        {
                            mainActivityPresenter.setLoading(false)
                            mainActivityPresenter.onError()
                        }
                )

    }

    override fun onRandomGivenJoke(mainContent: RelativeLayout, jokeUseCase: JokeUseCase, joke: String) {
        addViewWithRandomJoke(
                mainContent,
                joke,
                object : OnAnother {
                    override fun anotherOne(view: View) {
                        onRandomJoke(mainContent, jokeUseCase, mainActivityPresenter.isNoExplicit())
                    }
                }
        )
        mainActivityPresenter.setTitle(mainActivityPresenter.getContext().getString(R.string.random_joke))
        mainActivityPresenter.setLastLoaded(joke)

    }

    /**
     * Text Input Joke stuff
     */

    override fun getTextInputJokeView(jokeUseCase: JokeUseCase): TextInputJokeView {
        var textInputJokeView: TextInputJokeView? = null

        textInputJokeView = TextInputJokeView(mainActivityPresenter.getContext(), mainActivityPresenter)

        return textInputJokeView
    }

    override fun onGivenTextInput(mainContent: RelativeLayout, jokeUseCase: JokeUseCase, textInput: String, joke: String) {
        val textInputJokeView = addViewWithTextInputJoke(mainContent, jokeUseCase)
        textInputJokeView.displayJoke(joke)
        textInputJokeView.setInET(textInput)
        mainActivityPresenter.setLastLoaded(joke)
        mainActivityPresenter.setLastTextInputLoaded(textInput)
    }

    private fun addViewWithTextInputJoke(mainContent: RelativeLayout, jokeUseCase: JokeUseCase): TextInputJokeView {
        mainActivityPresenter.setLastLoaded(null)
        mainContent.removeAllViews()
        val textInputJokeView = getTextInputJokeView(jokeUseCase)
        mainContent.addView(
                textInputJokeView
        )

        return textInputJokeView
    }

    override fun onTextInput(mainContent: RelativeLayout, jokeUseCase: JokeUseCase) {
        addViewWithTextInputJoke(mainContent, jokeUseCase)
    }

    /**
     * Batch of Jokes
     */

    override fun getBatchJokeView(initList: ArrayList<String>?): BatchJokeView {
        return BatchJokeView(mainActivityPresenter, initList)
    }

    override fun addViewWithBatchRandomJokes(mainContent: RelativeLayout, initList: ArrayList<String>?): BatchJokeView {
        mainActivityPresenter.setLastLoaded(null)
        mainContent.removeAllViews()
        val batchJokeView = getBatchJokeView(initList)
        mainContent.addView(
                batchJokeView
        )

        return batchJokeView
    }

    override fun addViewWithGivenBatchRandomJokes(mainContent: RelativeLayout, initList: ArrayList<String>) {
        addViewWithBatchRandomJokes(mainContent, initList)
        mainActivityPresenter.setLastLoaded(initList)
    }

    /**
     * Activity stuff
     */

    companion object {
        val CURRENT_CATEGORY_KEY = "currentCategory"
        val LAST_KEY = "last"
        val TEXT_INPUT_KEY = "textInput"
    }

    override fun onSaveInstanceState(outState: Bundle?, currentCategory: String?, last: Any?, lastTextInput: String?) {
        if (currentCategory != null) {
            outState?.putString(CURRENT_CATEGORY_KEY, currentCategory)
        }
        if (last != null) {
            if (last is String) {
                outState?.putString(LAST_KEY, last)
            } else if (last is ArrayList<*>) {
                outState?.putSerializable(LAST_KEY, last)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        if (savedInstanceState == null || !savedInstanceState.containsKey(CURRENT_CATEGORY_KEY)) {
            return
        }

        var currentCategory: String = savedInstanceState.getString(CURRENT_CATEGORY_KEY)
        mainActivityPresenter.setCategory(currentCategory)

        when (currentCategory) {
            mainActivityPresenter.getContext().getString(R.string.random_joke) -> {
                if (savedInstanceState.get(LAST_KEY) is String) {
                    mainActivityPresenter.setGivenRandomJoke(savedInstanceState.getString(LAST_KEY))
                }
            }
            mainActivityPresenter.getContext().getString(R.string.custom_name) -> {
                val textInput: String = savedInstanceState.getString(TEXT_INPUT_KEY, "");
                val lastKey: String = savedInstanceState.getString(LAST_KEY, "");
                mainActivityPresenter.setGivenTextInputJoke(textInput, lastKey)

            }
            mainActivityPresenter.getContext().getString(R.string.never_ending) -> {
                if (savedInstanceState.getSerializable(LAST_KEY) is ArrayList<*>) {
                    @Suppress("UNCHECKED_CAST")
                    mainActivityPresenter.setGivenNeverEndingJokes(savedInstanceState.getSerializable(LAST_KEY) as ArrayList<String>)
                }
            }
        }
    }
}
