package uk.co.nbrown.cnjoke.RafaelRuiz.screens.main.activity.data

import io.reactivex.Single
import retrofit2.http.GET
import uk.co.nbrown.cnjoke.RafaelRuiz.screens.main.activity.data.model.JokeServerModel

/**
 * Created by rafaelruizmunoz on 08/11/2017.
 */
interface JokeService {

    @GET("random?escape=javascript")
    fun getRandomJoke() : Single<JokeServerModel>

    @GET("random?escape=javascript&exclude=[explicit]")
    fun getRandomJokeNoExplicit() : Single<JokeServerModel>
}