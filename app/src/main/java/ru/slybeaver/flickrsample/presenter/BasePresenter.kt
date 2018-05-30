package ru.slybeaver.flickrsample.presenter

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import ru.slybeaver.flickrsample.view.BaseView

/**
 * Created by psinetron on 29.05.2018.
 * http://slybeaver.ru
 */
abstract class BasePresenter:Presenter {

    private var compositeDisposable = CompositeDisposable()

    override fun onStop() {
        cancelAllDisposable()
    }

    fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    fun cancelAllDisposable() {
        compositeDisposable.clear()
    }

    protected abstract fun getView(): BaseView

    fun showLoadingState() {
        getView().showLoading()
    }

    fun hideLoadingState() {
        getView().hideLoading()
    }
}