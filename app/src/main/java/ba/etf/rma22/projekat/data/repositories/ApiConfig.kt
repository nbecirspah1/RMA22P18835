package ba.etf.rma22.projekat.data.repositories

import ba.etf.rma22.projekat.data.repositories.ApiConfig.Companion.baseURL

class ApiConfig {
    companion object{
        var baseURL : String = "https://rma22ws.herokuapp.com"
        }

    fun postaviBaseURL(baseUrl:String):Unit{
        baseURL=baseUrl
    }

    fun getURL() : String{
        return baseURL
    }

}