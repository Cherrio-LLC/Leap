package com.cherrio.leap.features.fav

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.cherrio.leap.data.local.AppDatabase
import com.cherrio.leap.data.model.DataEntity
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class FavViewModel(application: Application): AndroidViewModel(application) {
    private val dao = AppDatabase.getDatabase(getApplication()).dataEntityDao()
    private val _dataLiveData = MutableLiveData<List<DataEntity>>()
    val dataLiveData: LiveData<List<DataEntity>> = _dataLiveData
    init {
        viewModelScope.launch {
            val data = dao.getAllNewsData()
            _dataLiveData.postValue(data)

        }

    }
}