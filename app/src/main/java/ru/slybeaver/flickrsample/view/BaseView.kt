package ru.slybeaver.flickrsample.view

/**
 * Created by psinetron on 29.05.2018.
 * http://slybeaver.ru
 */
interface BaseView {

    fun showLoading()

    fun hideLoading()

    fun noConnectionError(method: Runnable, retried:Boolean)

    fun showError(errorCode:Int)

    fun showError(errorCode:String)

}