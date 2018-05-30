package ru.slybeaver.flickrsample.ui.activity

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import ru.slybeaver.flickrsample.R
import ru.slybeaver.flickrsample.presenter.Presenter
import ru.slybeaver.flickrsample.view.BaseView

/**
 * Created by psinetron on 29.05.2018.
 * http://slybeaver.ru
 */
abstract class BaseActivity : AppCompatActivity(), BaseView {

    protected abstract fun getPresenter(): Presenter?

    override fun onStop() {
        super.onStop()
        getPresenter()?.onStop()
    }

    /**
     * Обработчик ошибок. По идее в зависимости от кода ошибки можно делать свое событие.
     * В рамкам демки мы их просто выбрасываем в тост
     * @param errorCode - вернувшийся код ошибки
     */
    override fun showError(errorCode: Int) {
        Toast.makeText(this, getString(R.string.error_code, errorCode), Toast.LENGTH_LONG).show()
    }

    /**
     * Простой метод отображения текстовой ошибки
     * @param errorCode - вернувшийся код ошибки
     */
    override fun showError(errorCode: String) {
        Toast.makeText(this, errorCode, Toast.LENGTH_LONG).show()
    }

    /**
     * Показываем этап загрузки, если он присутствует на активности
     */
    override fun showLoading() {
        findViewById<ProgressBar>(R.id.mainProgressBar)?.let {
            it.visibility = View.VISIBLE
        }
    }

    /**
     * Скрываем экран активности, если он присутствует на активности
     */
    override fun hideLoading() {
        findViewById<ProgressBar>(R.id.mainProgressBar)?.let {
            it.visibility = View.GONE
        }
    }

    /**
     * Запускаем новую активность
     */
    fun openActivity(cls: Class<*>, bundle: Bundle?) {
        val intent = Intent(this, cls)
        bundle?.let { intent.putExtras(bundle) }
        startActivity(intent)
    }

    /**
     * В случае какой-либо ошибки в сети - выводим снэк с возможностью повтора
     */
    override fun noConnectionError(method: Runnable, retried: Boolean) {
        var LENGTH = Snackbar.LENGTH_INDEFINITE
        if (!retried) {
            LENGTH = Snackbar.LENGTH_SHORT
        }
        val mySnackbar = Snackbar.make((this
                .findViewById<View>(android.R.id.content) as ViewGroup).getChildAt(0),
                R.string.no_internet_connection, LENGTH)

        mySnackbar.setAction(R.string.retry_connection, { method.run() })
        mySnackbar.setActionTextColor(ContextCompat.getColor(this, R.color.colorAccent))
        mySnackbar.show()
    }

}