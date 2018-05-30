package ru.slybeaver.flickrsample.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import ru.slybeaver.flickrsample.R
import ru.slybeaver.flickrsample.dto.SearchListDTO
import ru.slybeaver.flickrsample.presenter.MainActivityPresenter
import ru.slybeaver.flickrsample.presenter.Presenter
import ru.slybeaver.flickrsample.ui.adapters.GridDecoration
import ru.slybeaver.flickrsample.ui.adapters.ImagesGridAdapter
import ru.slybeaver.flickrsample.utils.Const
import ru.slybeaver.flickrsample.view.MainActivityView
import java.util.*

/**
 * !!!Attenshion!!!
 * Примечание к проекту:
 * В связи с возможностями Kotlin совсем не вижу необходимости в использовании ButterKnife.
 * Все доступы итак разрешено использовать напрямую без всяких findViewById
 *
 * Кодеина здесь нет.
 *
 */

class MainActivity : BaseActivity(), MainActivityView {

    private val presenter: MainActivityPresenter = MainActivityPresenter(this)
    private val RESULT_SEARCH:Int = 1 //RequestCode для поиска
    private val COLUMN_NUMBER = 2 //Количество столбцов
    private var isLoading = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar?.let {
            setSupportActionBar(it)
        }

        imagesRecyclerView.layoutManager = GridLayoutManager(this, COLUMN_NUMBER)
        imagesRecyclerView.addItemDecoration(GridDecoration(this, R.dimen.grid_padding))
        imagesRecyclerView.adapter = ImagesGridAdapter(
                ArrayList(),
                clickListener = {
                    val bundle = Bundle()
                    bundle.putString("image_link", it)
                    openActivity(ImageActivity::class.java,bundle)
                })

        //Скроллинг "Догрузить изображения
        imagesRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val lastVisiblePosition = (imagesRecyclerView.layoutManager as GridLayoutManager).findLastCompletelyVisibleItemPosition()//смотрим сколько элементов на экране
                val totalItemCount = imagesRecyclerView.layoutManager.itemCount
                if (!isLoading) {
                    if (lastVisiblePosition >= totalItemCount - COLUMN_NUMBER) {
                        isLoading = true
                        presenter.loadMore({ showImagesList(it) })
                    }
                }
            }
        })

        //Загружаем рандомные картинки для стартового экрана
        presenter.loadImagesList(Random().nextInt(100).toString(), 0, { showImagesList(it) })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_search -> {
                val intent = Intent(this,SearchActivity::class.java)
                startActivityForResult(intent, RESULT_SEARCH)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun getPresenter(): Presenter? {
        return presenter
    }

    private fun showImagesList(photos: SearchListDTO.Photos) {
        if (presenter.currentPage < presenter.totalPages) {
            isLoading = false
        }
        toolbar.title = getString(R.string.result_for, presenter.lastFindText)
        (imagesRecyclerView.adapter as ImagesGridAdapter).updateList(photos.photos)
        (imagesRecyclerView.adapter as ImagesGridAdapter).notifyDataSetChanged()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode== Activity.RESULT_OK && requestCode == RESULT_SEARCH) {
            data?.let {
                (imagesRecyclerView.adapter as ImagesGridAdapter).clearList()
                presenter.loadImagesList(it.getStringExtra(Const.instance.SEARCH_RESULT_KEY), 0, { showImagesList(it) })
            }
        }
    }
}
