package com.cherrio.leap.features.allnews

import android.app.Application
import android.content.Context
import androidx.lifecycle.*
import androidx.paging.cachedIn
import com.cherrio.leap.LeapApp
import com.cherrio.leap.data.local.AppDatabase
import com.cherrio.leap.data.model.Data
import com.cherrio.leap.data.model.DataEntity
import com.cherrio.leap.data.remote.Result
import com.cherrio.leap.domain.usecase.AllNewsUseCase
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(allNewsUseCase: AllNewsUseCase, application: Application) :
    AndroidViewModel(application) {
    private val _newsLiveData = MutableLiveData<Result<List<Data>>>()
    val newsLiveData: LiveData<Result<List<Data>>> get() = _newsLiveData
    val newsFlow = allNewsUseCase.invoke()
        .cachedIn(viewModelScope)

    fun addToFavorites(data: Data) {
        viewModelScope.launch {
            val dao = AppDatabase.getDatabase(getApplication()).dataEntityDao()
            dao.addData(DataEntity.mapToDataEntity(data))
        }
    }

}