package uk.co.nbrown.cnjoke.RafaelRuiz.screens.main.activity.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by rafaelruizmunoz on 08/11/2017.
 */

class JokeBatchServerModel : Serializable {

    @SerializedName("value")
    private lateinit var jokeValuesServer: ArrayList<JokeValueServer>

    fun getJokes(): ArrayList<String> {
        val list: ArrayList<String> = ArrayList()

        jokeValuesServer.forEach { jokeValueServer ->
            list.add(jokeValueServer.joke!!)
        }

        return list
    }

}