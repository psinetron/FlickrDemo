package ru.slybeaver.flickrsample.model

import io.reactivex.Single
import ru.slybeaver.flickrsample.dto.SearchListDTO

/**
 * Created by psinetron on 29.05.2018.
 * http://slybeaver.ru
 */
interface ModelApi {

    fun search(text:String, page:Int): Single<SearchListDTO>
}