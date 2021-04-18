package com.mobikasa.recyclerviewsample.services

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitManager {

    companion object{
        private const val BASE_URL = "https://itunes.apple.com/"
        const val  GET_API_SEARCH = "search?term=Michael+jackson"

        private val mOkHttpClient = OkHttpClient.Builder().build()
        private val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(mOkHttpClient).build()

        fun getRestAPIService():RestAPI{
            return  retrofit.create(RestAPI::class.java)
        }
    }
}