package com.cherrio.leap.features.gallery

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.cherrio.leap.data.model.ImageFolder
import com.cherrio.leap.data.model.Images
import com.cherrio.leap.domain.usecase.GetImagesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.N)
@HiltViewModel
class GalleryViewModel @Inject constructor(
     application: Application,
    private val getImagesUseCase: GetImagesUseCase
) : AndroidViewModel(application) {
    private val _foldersWithImages = MutableLiveData<MutableList<ImageFolder>>()
    val foldersWithImages = _foldersWithImages

    init {
        _foldersWithImages.value = getImagesUseCase.invoke(getApplication())
    }

}