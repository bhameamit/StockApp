package com.stocks.stockprice.presentation.main

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stocks.stockprice.MainActivity
import com.stocks.stockprice.domain.common.BaseResult
import com.stocks.stockprice.domain.stocks.entity.StockEntity
import com.stocks.stockprice.domain.stocks.usecase.GetAllStocksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val getAllStocksUseCase: GetAllStocksUseCase): ViewModel() {

    val TAG = "MainActivityViewModel"

    private val state = MutableStateFlow<MainActivityState>(MainActivityState.Init)
    val mState : StateFlow<MainActivityState> get() = state
    private val stocks = MutableStateFlow<List<StockEntity>>(mutableStateListOf())
    val mStocks: StateFlow<List<StockEntity>> get() = stocks

    init {
        fetchAllStocks()
    }
    fun fetchAllStocks(){
        viewModelScope.launch {
            getAllStocksUseCase.invoke()
                .collect{
                    Log.d(TAG, "Received getAllStocksUseCase response: " + it as BaseResult)
                    when(it){
                        is BaseResult.Success -> {
                            Log.d(TAG, "Received getAllStocksUseCase response: " + it.data)
                            stocks.value = it.data
                            Log.d(TAG, "Received getAllStocksUseCase response: " + stocks.value )
                            val stockEntity = stocks.value.get(0)
                            Log.d(TAG, "Received getAllStocksUseCase response: " + stockEntity )
                            val flowEntity = mStocks.value.get(0)
                            Log.d(TAG, "Received getAllStocksUseCase response: " + flowEntity )
                        }
                        is BaseResult.Error -> {
                            Log.e(TAG, "fetchAllStocks error!!")
                        }
                    }

                }
        }
    }
}

sealed class MainActivityState{
    object Init: MainActivityState()
}