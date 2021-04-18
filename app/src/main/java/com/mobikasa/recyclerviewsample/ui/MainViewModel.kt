package com.mobikasa.recyclerviewsample.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.mobikasa.recyclerviewsample.services.MainRepository
import com.mobikasa.recyclerviewsample.services.Resource

class MainViewModel(mMainRepository: MainRepository) : ViewModel() {

    var mData = liveData {
        emit(Resource.loading(null))
        try {
            val data = mMainRepository.doGetAllSongs()
            emit(Resource.success(data = data.body()?.results))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error occured"))
        }
    }
}