package uk.co.nbrown.cnjoke.RafaelRuiz.screens

import android.app.Application
import com.google.gson.Gson
import retrofit2.Retrofit
import uk.co.nbrown.cnjoke.RafaelRuiz.R
import uk.co.nbrown.cnjoke.RafaelRuiz.factories.gson.GsonFactory
import uk.co.nbrown.cnjoke.RafaelRuiz.factories.retrofit.RetrofitFactory
import uk.co.nbrown.cnjoke.RafaelRuiz.screens.ChuckNorrisJokeApplication.Singleton.gson
import uk.co.nbrown.cnjoke.RafaelRuiz.screens.ChuckNorrisJokeApplication.Singleton.retrofit

/**
 * Created by rafaelruizmunoz on 08/11/2017.
 */

class ChuckNorrisJokeApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        application = this
    }

    object Singleton {
        var retrofit: Retrofit? = null
        var gson: Gson? = null
    }

    fun getGson(): Gson? {
        if (gson == null) {
            gson = GsonFactory.createGson()
        }

        return gson
    }

    fun getRetrofit(): Retrofit {
        if (retrofit == null) {
            retrofit = RetrofitFactory.createRetrofit(
                    getString(R.string.baseUrl))
        }

        return retrofit!!
    }

    companion object {
        var application: ChuckNorrisJokeApplication? = null
            private set
    }
}
