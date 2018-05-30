package ru.slybeaver.flickrsample.presenter

import io.realm.Realm
import ru.slybeaver.flickrsample.dto.SearchHistoryDTO
import ru.slybeaver.flickrsample.view.BaseView
import ru.slybeaver.flickrsample.view.SearchView
import java.util.*

/**
 * Created by psinetron on 30.05.2018.
 * http://slybeaver.ru
 */
class SearchPresenter(private val view: SearchView) : BasePresenter() {
    override fun getView(): BaseView {
        return view
    }

    /**
     * Записываем поисковую строку в историю
     * @param text - поисковый запрос
     */
    fun setHistory(text: String) {
        val realm = Realm.getDefaultInstance()
        realm.beginTransaction()
        val history = SearchHistoryDTO()
        history.searchText = text
        history.searchDate = Calendar.getInstance().timeInMillis
        realm.insertOrUpdate(history)

        //Ограничим количество имеющихся записей 10-ю
        val realmResult = realm.where(SearchHistoryDTO::class.java).distinct("searchDate").findAll()
        if (realmResult.size>10) {
                realmResult.get(10)?.deleteFromRealm()
        }
        realm.commitTransaction()
    }

    /**
     * Получаем последние использованные строки поиска
     * @return Коллекцию с результатами
     */
    fun getHistory(): ArrayList<SearchHistoryDTO> {
        val result = ArrayList<SearchHistoryDTO>()
        val realm = Realm.getDefaultInstance()
        val realmResult = realm.where(SearchHistoryDTO::class.java).distinct("searchDate").findAll()
        realmResult.size
        for (value in realmResult) {
            result.add(value)
        }
        return result
    }

}