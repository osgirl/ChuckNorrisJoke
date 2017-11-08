package uk.co.nbrown.cnjoke.RafaelRuiz.screens.main.activity.data.model

import com.google.gson.annotations.SerializedName

import java.io.Serializable

/**
 * Created by rafaelruizmunoz on 08/11/2017.
 */

class JokeValueServer : Serializable {

    @SerializedName("joke")
    var joke: String? = null

}
