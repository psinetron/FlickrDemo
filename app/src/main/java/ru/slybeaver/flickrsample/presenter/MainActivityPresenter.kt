package ru.slybeaver.flickrsample.presenter

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.slybeaver.flickrsample.dto.SearchListDTO
import ru.slybeaver.flickrsample.model.ModelApiImpl
import ru.slybeaver.flickrsample.view.BaseView
import ru.slybeaver.flickrsample.view.MainActivityView

/**
 * Created by psinetron on 29.05.2018.
 * http://slybeaver.ru
 */
class MainActivityPresenter(private val view: MainActivityView) : BasePresenter() {

    var lastFindText:String? = null
    var totalPages:Int = 0
    var currentPage:Int = 0

    override fun getView(): BaseView {
        return view
    }

    fun loadImagesList(text: String, page:Int , listener: (SearchListDTO.Photos) -> Unit) {
        cancelAllDisposable()
        showLoadingState()
        lastFindText = text
        currentPage = page
        val subscriber = ModelApiImpl().search(text, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            hideLoadingState()
                            if (it.stat == "ok") {
                                it.result?.let{
                                    it.pages?.let { totalPages = it }
                                    listener(it)
                                }
                            } else {
                                it.errorCode?.let { view.showError(it) }
                            }
                        },
                        {
                            hideLoadingState()
                            view.noConnectionError(Runnable { loadImagesList(text, page, listener) }, true)
                        }
                )
        addDisposable(subscriber)
    }

    fun loadMore(listener: (SearchListDTO.Photos) -> Unit) {
        lastFindText?.let {
            loadImagesList(it,currentPage+1,listener)
        }
    }


}