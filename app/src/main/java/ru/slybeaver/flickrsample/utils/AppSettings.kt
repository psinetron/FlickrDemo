package ru.slybeaver.flickrsample.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences

/**
 * Created by psinetron on 29.05.2018.
 * http://slybeaver.ru
 */
class AppSettings private constructor() {

    private val SHARED_OPTIONS = "ProjectOptions"
    private var sPref: SharedPreferences? = null
    private var editor: SharedPreferences.Editor? = null

    private object Holder {
        val INSTANCE = AppSettings()
    }

    companion object {
        val instance: AppSettings by lazy { Holder.INSTANCE }
    }

    @SuppressLint("CommitPrefEdits")
    fun initInstance(context: Context?) {
        sPref = context?.getSharedPreferences(SHARED_OPTIONS, Context.MODE_PRIVATE)
        editor = sPref?.edit()
    }

}