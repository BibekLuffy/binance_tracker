package com.enfiny.binancetracker.ui.binancelist

import androidx.lifecycle.*
import com.enfiny.binancetracker.data.room.entity.MyPortfolio
import com.enfiny.binancetracker.network.Resource
import com.enfiny.binancetracker.network.response.PriceResponse
import com.enfiny.binancetracker.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import java.util.*
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

    fun update(myPortfolio: MyPortfolio) = viewModelScope.launch {
        mainRepository.update(myPortfolio)
    }

    fun delete(pId: Int) = viewModelScope.launch {
        mainRepository.delete(pId)
    }


    private val _priceResponse: MutableLiveData<Resource<PriceResponse>> =
        MutableLiveData()
    val priceResponse: LiveData<Resource<PriceResponse>>
        get() = _priceResponse
    val bS: MutableLiveData<String> = MutableLiveData("")
    val sS: MutableLiveData<String> = MutableLiveData("")
    val q: MutableLiveData<String> = MutableLiveData("")
    val quantity: MutableLiveData<String> = MutableLiveData("")
    val f: MutableLiveData<String> = MutableLiveData("")
    val cP: MutableLiveData<String> = MutableLiveData("")
    val loading: MutableLiveData<Boolean> = MutableLiveData(false)
    val isEdit: MutableLiveData<Boolean> = MutableLiveData(false)
    val pId: MutableLiveData<Int> = MutableLiveData(0)


    fun onDone() = viewModelScope.launch {
        if (!loading.value!!) {
            loading.value = true
            _priceResponse.value = mainRepository.getPrice(
                bS.value.toString()
                    .uppercase(Locale.getDefault()) + sS.value.toString()
                    .uppercase(Locale.getDefault())
            )
        }
    }
}