package uk.co.nbrown.cnjoke.RafaelRuiz.screens.main.activity.domain

import io.reactivex.Single
import uk.co.nbrown.cnjoke.RafaelRuiz.screens.main.activity.data.JokeRepository

/**
 * Created by rafaelruizmunoz on 08/11/2017.
 */

class JokeUseCase constructor(private val locationsRepository: JokeRepository) {

    fun getRandomJoke(noExplicit: Boolean): Single<String> {
        return locationsRepository.getRandomJoke(noExplicit)
                .map { jokeFromServer -> jokeFromServer.getJoke() }
    }

    fun getJokeWithMainCharacter(mainCharacter: String, noExplicit: Boolean) : Single<String> {
        return locationsRepository.getJokeWithMainCharacter(mainCharacter, noExplicit)
                .map { jokeFromServer -> jokeFromServer.getJoke() }
    }

    fun getBatchJokes(noExplicit: Boolean) : Single<ArrayList<String>> {
        return locationsRepository.getBatchJokes(noExplicit)
                .map { jokeBatchFromServer -> jokeBatchFromServer.getJokes() }
    }

    object Singleton {
        var instance: JokeUseCase? = null
    }

    companion object {

        fun getInstance(): JokeUseCase {
            if (Singleton.instance == null) {
                Singleton.instance = JokeUseCase(
                        JokeRepository.getInstance()
                )
            }

            return Singleton.instance!!
        }
    }
}
