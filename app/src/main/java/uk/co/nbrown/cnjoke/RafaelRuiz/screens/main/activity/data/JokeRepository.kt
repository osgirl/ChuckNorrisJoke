package uk.co.nbrown.cnjoke.RafaelRuiz.screens.main.activity.data

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import uk.co.nbrown.cnjoke.RafaelRuiz.screens.ChuckNorrisJokeApplication
import uk.co.nbrown.cnjoke.RafaelRuiz.screens.main.activity.data.model.JokeServerModel

/**
 * Created by rafaelruizmunoz on 08/11/2017.
 */

class JokeRepository constructor(private val jokeService: JokeService) {

    object Singleton {
        var instance: JokeRepository? = null
    }

    companion object {
        fun getInstance(): JokeRepository {
            if (Singleton.instance == null) {
                Singleton.instance = JokeRepository(
                        ChuckNorrisJokeApplication.application!!.getRetrofit().create(JokeService::class.java)
                )
            }

            return Singleton.instance!!
        }
    }

    fun getRandomJoke(noExplicit: Boolean): Single<JokeServerModel> {
        val single: Single<JokeServerModel> = if (noExplicit) {
            jokeService.getRandomJokeNoExplicit()
        } else {
            jokeService.getRandomJoke()
        }

        return single
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

}