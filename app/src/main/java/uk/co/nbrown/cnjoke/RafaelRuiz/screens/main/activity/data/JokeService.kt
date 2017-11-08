package uk.co.nbrown.cnjoke.RafaelRuiz.screens.main.activity.data

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import uk.co.nbrown.cnjoke.RafaelRuiz.screens.main.activity.data.model.JokeBatchServerModel
import uk.co.nbrown.cnjoke.RafaelRuiz.screens.main.activity.data.model.JokeServerModel

/**
 * Created by rafaelruizmunoz on 08/11/2017.
 */
interface JokeService {

    @GET("random?escape=javascript")
    fun getRandomJoke(): Single<JokeServerModel>

    @GET("random?escape=javascript&exclude=[explicit]")
    fun getRandomJokeNoExplicit(): Single<JokeServerModel>

    @GET("random?escape=javascript")
    fun getJokeWithMainCharacter(@Query("firstName") mainCharacter: String): Single<JokeServerModel>

    @GET("random?escape=javascript&exclude=[explicit]")
    fun getJokeWithMainCharacterNoExplicit(@Query("firstName") mainCharacter: String): Single<JokeServerModel>

    @GET("random/20?escape=javascript")
    fun get20RandomJokes(): Single<JokeBatchServerModel>

    @GET("random/20?escape=javascript&exclude=[explicit]")
    fun get20RandomJokesNoExplicit(): Single<JokeBatchServerModel>
}