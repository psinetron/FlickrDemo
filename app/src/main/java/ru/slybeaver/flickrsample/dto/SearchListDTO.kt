package ru.slybeaver.flickrsample.dto

import com.google.gson.annotations.SerializedName

/**
 * Created by psinetron on 29.05.2018.
 * http://slybeaver.ru
 */
class SearchListDTO {

    @SerializedName("photos") var result:Photos? = null
    @SerializedName("stat") var stat:String? = null //defaul "ok"
    @SerializedName("code") var errorCode:Int? = null

    inner class Photos {
        @SerializedName("page") var page:Int? = null
        @SerializedName("pages") var pages:Int? = null
        @SerializedName("photo") var photos:ArrayList<PhotoDetail> = ArrayList()
    }

    inner class  PhotoDetail {
        @SerializedName("id") var id:String? = null
        @SerializedName("owner") var owner:String? = null
        @SerializedName("secret") var secret:String? = null
        @SerializedName("server") var server:String? = null
        @SerializedName("farm") var farm:String? = null
        @SerializedName("title") var title:String? = null
    }

}