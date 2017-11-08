package uk.co.nbrown.cnjoke.RafaelRuiz.screens.main.activity.ui.presenter

import android.support.design.widget.Snackbar
import android.view.View
import android.widget.RelativeLayout
import uk.co.nbrown.cnjoke.RafaelRuiz.R
import uk.co.nbrown.cnjoke.RafaelRuiz.screens.main.activity.domain.JokeUseCase
import uk.co.nbrown.cnjoke.RafaelRuiz.screens.main.jokeView.ui.view.JokeView
import uk.co.nbrown.cnjoke.RafaelRuiz.screens.main.jokeView.ui.view.OnAnother

/**
 * Created by rafaelruizmunoz on 08/11/2017.
 */

class MainActivityLogic constructor(private var mainActivityPresenter: MainActivityPresenter) : MainActivityLogicInterface {

    override fun getRandomJokeView(joke: String, onAnother: OnAnother): JokeView {
        return JokeView(mainActivityPresenter.getContext(), joke, onAnother)
    }

    override fun onRandomJoke(mainContent: RelativeLayout, jokeUseCase: JokeUseCase, noExplicit: Boolean) {
        mainActivityPresenter.setLoading(true)

        jokeUseCase.getRandomJoke(noExplicit)
                .subscribe(
                        { joke ->

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
                            Snackbar.make(mainContent, R.string.Theres_been_an_error, Snackbar.LENGTH_LONG).show()
                        }
                )

    }

    private fun addViewWithRandomJoke(mainContent: RelativeLayout, joke: String, onAnother: OnAnother) {
        mainContent.removeAllViews()
        mainContent.addView(
                getRandomJokeView(joke, onAnother)
        )
    }

}
