package ru.slybeaver.flickrsample.model

import io.reactivex.Single
import ru.slybeaver.flickrsample.dto.SearchListDTO
import ru.slybeaver.flickrsample.utils.Const

/**
 * Created by psinetron on 29.05.2018.
 * http://slybeaver.ru
 */
class ModelApiImpl: ModelApi {


    private val apiInterface = ApiModule.getApiInterface(Const.instance.SEARCH_ADDRESS)

    override fun search(text: String, page:Int): Single<SearchListDTO> {
        return apiInterface.search("flickr.photos.search",
                Const.instance.FLICKR_KEY,
                "json",
                "1",
                page,
                12,
                text)
    }
}