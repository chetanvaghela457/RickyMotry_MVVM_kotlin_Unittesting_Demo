package com.kotlin.mvvm.unittestdemo.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotlin.mvvm.unittestdemo.domain.repository.RickAndMortyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashActivityViewModel @Inject constructor(
    private val repository: RickAndMortyRepository
) : ViewModel() {

    private var _readAppState = MutableLiveData<Boolean>()
    val readAppState :LiveData<Boolean> get() = _readAppState

    fun saveAppFirstOpenState(state:Boolean){
        viewModelScope.launch(Dispatchers.IO) {
            repository.saveAppFirstOpenState(state)
        }
    }
    fun readAppFirstOpenState(){
        viewModelScope.launch {
            repository.getIsAppFirstOpenState().collectLatest{
                _readAppState.postValue(it)
            }
        }
    }
}