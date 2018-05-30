package ru.slybeaver.flickrsample.utils

/**
 * Created by psinetron on 29.05.2018.
 * http://slybeaver.ru
 */
class Const private constructor(){

    private object Holder {
        val INSTANCE = Const()
    }

    companion object {
        val instance: Const by lazy { Holder.INSTANCE }
    }

    val SEARCH_ADDRESS = "https://api.flickr.com/services/rest/"

    val FLICKR_KEY = "34e681d12028491b8d7ef7472d378290"
    val FLICK_SECRET_KEY = "50095bbbeb87517b"

    val SEARCH_RESULT_KEY = "SearchResult"
}