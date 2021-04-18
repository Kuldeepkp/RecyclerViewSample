package com.mobikasa.recyclerviewsample.services

import com.mobikasa.recyclerviewsample.models.ServiceResponse
import retrofit2.Response

class MainRepository {
    private val restAPI = RetrofitManager.getRestAPIService()
    suspend fun doGetAllSongs(): Response<ServiceResponse> {
        return restAPI.doGetAllSongs()
    }
}