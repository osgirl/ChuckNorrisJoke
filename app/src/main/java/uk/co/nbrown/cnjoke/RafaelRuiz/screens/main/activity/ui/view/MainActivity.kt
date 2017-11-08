package uk.co.nbrown.cnjoke.RafaelRuiz.screens.main.activity.ui.view


import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.SwitchCompat
import android.view.View
import android.widget.RelativeLayout
import butterknife.BindView
import butterknife.ButterKnife
import com.github.clans.fab.FloatingActionMenu
import uk.co.nbrown.cnjoke.RafaelRuiz.R
import uk.co.nbrown.cnjoke.RafaelRuiz.screens.main.activity.domain.JokeUseCase
import uk.co.nbrown.cnjoke.RafaelRuiz.screens.main.activity.ui.presenter.MainActivityLogic
import uk.co.nbrown.cnjoke.RafaelRuiz.screens.main.activity.ui.presenter.MainActivityLogicInterface
import uk.co.nbrown.cnjoke.RafaelRuiz.screens.main.activity.ui.presenter.MainActivityPresenter


class MainActivity : AppCompatActivity(), MainActivityPresenter {

    @BindView(R.id.mainContent)
    lateinit var mainContent: RelativeLayout

    @BindView(R.id.toolbarTitleTV)
    lateinit var toolbarTitleTV: AppCompatTextView

    @BindView(R.id.switchCompat)
    lateinit var switchCompat: SwitchCompat

    @BindView(R.id.faMenu)
    lateinit var faMenu: FloatingActionMenu

    var currentCategory: String? = null

    val logic: MainActivityLogicInterface = MainActivityLogic(this)

    var jokeUseCase: JokeUseCase = JokeUseCase.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
        logic.onCreate(savedInstanceState)

        configureViews()
        setOnClickListeners()

    }

    private fun configureViews() {
        configureFaMenu()
    }

    override fun onRandomJoke() {
        setCategory(getString(R.string.random_joke))
        setTitle(currentCategory)
        logic.onRandomJoke(mainContent, jokeUseCase, isNoExplicit())
    }

    override fun isNoExplicit(): Boolean {
        return switchCompat.isChecked
    }

    override fun onTextInput() {
        setCategory(getString(R.string.custom_name))
        setTitle(currentCategory)
        logic.onTextInput(mainContent, jokeUseCase)
    }

    override fun onNeverEndingJoke() {
        setCategory(getString(R.string.never_ending))
        setTitle(currentCategory)
        logic.addViewWithBatchRandomJokes(mainContent, null)
    }

    override fun setCategory(category: String?) {
        currentCategory = category
    }

    override fun setLoading(loading: Boolean) {
        setTitle(
                if (loading) getString(R.string.Loading) else currentCategory
        )
    }

    override fun onError() {
        Snackbar.make(mainContent, R.string.Theres_been_an_error, Snackbar.LENGTH_LONG).show()
    }

    override fun onNoNameInput() {
        Snackbar.make(mainContent, R.string.Please_type_a_name, Snackbar.LENGTH_SHORT).show()
    }

    override fun configureFaMenu() {
        faMenu.setClosedOnTouchOutside(true)
    }

    override fun getContext(): Context {
        return this
    }

    override fun closeFaMenu() {
        faMenu.close(true);
    }

    override fun setTitle(title: String?) {
        toolbarTitleTV.text = title ?: getString(R.string.app_name)
    }

    fun setOnClickListeners() {
        findViewById<View>(R.id.randomJokeItem).setOnClickListener({ _ ->
            onRandomJoke()
            closeFaMenu()
        })

        findViewById<View>(R.id.textInputItem).setOnClickListener({ _ ->
            onTextInput()
            closeFaMenu()
        })

        findViewById<View>(R.id.neverEndingJokeItem).setOnClickListener({ _ ->
            onNeverEndingJoke()
            closeFaMenu()
        })
    }

    var last: Any? = null
    override fun setLastLoaded(any: Any?) {
        last = any
    }

    var lastTextInput: String? = null
    override fun setLastTextInputLoaded(lastTI: String) {
        lastTextInput = lastTI
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        logic.onSaveInstanceState(outState, currentCategory, last, lastTextInput)
    }

    override fun setGivenRandomJoke(joke: String) {
        logic.onRandomGivenJoke(mainContent, jokeUseCase, joke)
    }

    override fun setGivenTextInputJoke(textInputText: String, joke: String) {
        logic.onGivenTextInput(mainContent, jokeUseCase, textInputText, joke)
    }

    override fun setGivenNeverEndingJokes(list: ArrayList<String>) {
        logic.addViewWithGivenBatchRandomJokes(mainContent, list)
    }

}
