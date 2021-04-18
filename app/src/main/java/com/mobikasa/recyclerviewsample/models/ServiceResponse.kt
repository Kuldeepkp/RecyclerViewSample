package com.mobikasa.recyclerviewsample.models
import com.google.gson.annotations.SerializedName

data class ServiceResponse(
    @SerializedName("resultCount")
    val resultCount: Int?,
    @SerializedName("results")
    val results: List<Result>?
)