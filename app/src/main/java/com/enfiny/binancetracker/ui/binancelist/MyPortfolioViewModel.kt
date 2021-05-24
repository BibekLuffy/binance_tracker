package com.enfiny.binancetracker.ui.binancelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.enfiny.binancetracker.data.room.entity.MyPortfolio
import com.enfiny.binancetracker.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyPortfolioViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {
    val getMyPortfolioData: LiveData<List<MyPortfolio>> = mainRepository.getMyPortfolio
        .flowOn(Dispatchers.Main).asLiveData(viewModelScope.coroutineContext)

    fun insert(myPortfolio: MyPortfolio) = viewModelScope.launch {
        mainRepository.insert(myPortfolio)
    }
}