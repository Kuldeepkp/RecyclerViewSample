package com.mobikasa.recyclerviewsample.services

import com.mobikasa.recyclerviewsample.models.ServiceResponse
import retrofit2.Response
import retrofit2.http.GET

interface RestAPI {
    @GET(RetrofitManager.GET_API_SEARCH)
    suspend fun doGetAllSongs(): Response<ServiceResponse>
}