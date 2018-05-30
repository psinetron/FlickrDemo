package ru.slybeaver.flickrsample.dto

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by psinetron on 30.05.2018.
 * http://slybeaver.ru
 */
open class SearchHistoryDTO:RealmObject() {

    @PrimaryKey
    var searchText:String? = null
    var searchDate:Long = 0

}