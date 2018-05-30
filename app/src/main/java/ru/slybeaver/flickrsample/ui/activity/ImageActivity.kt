package ru.slybeaver.flickrsample.ui.activity

import android.os.Bundle
import android.view.MenuItem
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_fullimage.*
import ru.slybeaver.flickrsample.R
import ru.slybeaver.flickrsample.presenter.Presenter

/**
 * Created by psinetron on 29.05.2018.
 * http://slybeaver.ru
 */
class ImageActivity:BaseActivity() {
    override fun getPresenter(): Presenter? {
        return null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fullimage)

        toolbar?.let { setSupportActionBar(it) }
        supportActionBar?.let {
            it.setHomeButtonEnabled(true)
            it.setDisplayHomeAsUpEnabled(true)
        }

        intent.extras?.let {
            showLoading()
            Picasso.with(this)
                    .load(it.getString("image_link", ""))
                    .into(imgFull, object : Callback {
                        override fun onSuccess() {
                            hideLoading()
                        }

                        override fun onError() {
                            hideLoading()
                            showError(R.string.image_load_failed)
                        }

                    })
        }?: run {
            showError(R.string.image_load_failed)
        }




    }

    override fun onOptionsItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(menuItem)
    }

}