package uk.co.nbrown.cnjoke.RafaelRuiz.screens.main.batchJokeView.ui.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.widget.RelativeLayout
import butterknife.BindView
import butterknife.ButterKnife
import uk.co.nbrown.cnjoke.RafaelRuiz.R
import uk.co.nbrown.cnjoke.RafaelRuiz.screens.main.activity.domain.JokeUseCase
import uk.co.nbrown.cnjoke.RafaelRuiz.screens.main.activity.ui.presenter.MainActivityPresenter
import uk.co.nbrown.cnjoke.RafaelRuiz.screens.main.batchJokeView.ui.presenter.BatchJokeLogic
import uk.co.nbrown.cnjoke.RafaelRuiz.screens.main.batchJokeView.ui.presenter.BatchJokeViewPresenter

/**
 * Created by rafaelruizmunoz on 08/11/2017.
 */

class BatchJokeView(private val mainActivityPresenter: MainActivityPresenter, var initList: ArrayList<String>?) : RelativeLayout(mainActivityPresenter.getContext()), BatchJokeViewPresenter {

    @BindView(R.id.recyclerView)
    lateinit var recyclerView: RecyclerView

    lateinit var batchJokeLogic: BatchJokeLogic

    init {
        initView()
        configureRecyclerView()
    }

    private fun configureRecyclerView() {
        batchJokeLogic.configureRecyclerView(recyclerView);
    }

    override fun initView() {
        LayoutInflater.from(context).inflate(R.layout.view_batch_jokes, this)
        ButterKnife.bind(this)

        batchJokeLogic = BatchJokeLogic(
                JokeUseCase.getInstance(),
                mainActivityPresenter
        )

        if (initList == null) {
            loadMoreJokes()
        } else {
            batchJokeLogic.setUpJokes(recyclerView, initList!!)
        }
    }

    override fun loadMoreJokes() {
        batchJokeLogic.loadBatches(recyclerView)
    }


}
