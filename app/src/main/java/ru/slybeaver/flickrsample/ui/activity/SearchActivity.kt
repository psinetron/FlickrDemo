package ru.slybeaver.flickrsample.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.inputmethod.EditorInfo
import kotlinx.android.synthetic.main.activity_search.*
import ru.slybeaver.flickrsample.R
import ru.slybeaver.flickrsample.presenter.Presenter
import ru.slybeaver.flickrsample.presenter.SearchPresenter
import ru.slybeaver.flickrsample.ui.adapters.HistoryListAdapter
import ru.slybeaver.flickrsample.utils.Const
import ru.slybeaver.flickrsample.view.SearchView

/**
 * Created by psinetron on 29.05.2018.
 * http://slybeaver.ru
 */
class SearchActivity : BaseActivity(), SearchView {

    private val presenter: SearchPresenter = SearchPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        btnSearch.setOnClickListener { returnResult(txtSearchText.text.toString()) }
        btnBack.setOnClickListener { onBackPressed() }

        txtSearchText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                returnResult(txtSearchText.text.toString())
            }
            false
        }

        historyRecyclerView.layoutManager = LinearLayoutManager(this)
        historyRecyclerView.adapter = HistoryListAdapter(presenter.getHistory(), {
            txtSearchText.setText(it)
            returnResult(txtSearchText.text.toString())
        })

    }

    override fun getPresenter(): Presenter? {
        return presenter
    }

    override fun onBackPressed() {
        setResult(Activity.RESULT_CANCELED, Intent())
        super.onBackPressed()
    }

    private fun returnResult(searchText: String) {
        presenter.setHistory(searchText)
        val intent = Intent()
        intent.putExtra(Const.instance.SEARCH_RESULT_KEY, searchText)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }


}