package uk.co.nbrown.cnjoke.RafaelRuiz.screens.main.activity.ui.view


import android.content.Context
import android.os.Bundle
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

    val logic: MainActivityLogicInterface = MainActivityLogic(this)

    var jokeUseCase: JokeUseCase = JokeUseCase.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)

        setOnClickListeners()

    }

    override fun onRandomJoke() {
        logic.onRandomJoke(mainContent, jokeUseCase, isNoExplicit())
    }

    override fun isNoExplicit(): Boolean {
        return switchCompat.isChecked
    }

    override fun onTextInput() {

    }

    override fun onNeverEndingJoke() {

    }

    override fun setLoading(loading: Boolean) {
        setTitle(
                getString(if (loading) R.string.Loading else R.string.app_name)
        )
    }

    override fun getContext(): Context {
        return this
    }

    override fun closeFaMenu() {
        faMenu.close(true);
    }

    override fun setTitle(title: String) {
        toolbarTitleTV.text = title
    }

    override fun setOnClickListeners() {
        findViewById<View>(R.id.randomJokeItem).setOnClickListener({ _ ->
            onRandomJoke()
            closeFaMenu()
        })
    }

}
