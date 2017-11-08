package uk.co.nbrown.cnjoke.RafaelRuiz.screens.main.activity.data.model

import com.google.gson.annotations.SerializedName

import java.io.Serializable

/**
 * Created by rafaelruizmunoz on 08/11/2017.
 */

class JokeServerModel : Serializable {

    @SerializedName("value")
    private lateinit var jokeValueServer: JokeValueServer

    fun getJoke() : String {
        return jokeValueServer.joke!!
    }

}
