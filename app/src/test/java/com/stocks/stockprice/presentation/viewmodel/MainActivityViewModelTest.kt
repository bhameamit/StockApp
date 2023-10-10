package com.stocks.stockprice.presentation.viewmodel

import com.stocks.stockprice.data.stock.utils.DataResponse
import com.stocks.stockprice.data.stock.utils.MetaResponse
import com.stocks.stockprice.data.stock.utils.StockResponse
import com.stocks.stockprice.domain.common.BaseResult
import com.stocks.stockprice.domain.stocks.entity.StockEntity
import com.stocks.stockprice.domain.stocks.interfaces.StockRepository
import com.stocks.stockprice.domain.stocks.usecase.GetAllStocksUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
@OptIn(ExperimentalCoroutinesApi::class)
class MainActivityViewModelTest {

    lateinit var getAllStocksUseCase: GetAllStocksUseCase
    lateinit var mainActivityViewModel: MainActivityViewModel
    private val mainThreadSurrogate = newSingleThreadContext("UI Thread")

    @Mock
    lateinit var mockStockRepository: StockRepository


    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
        MockitoAnnotations.initMocks(this)
        getAllStocksUseCase = GetAllStocksUseCase(mockStockRepository)
        mainActivityViewModel = MainActivityViewModel(getAllStocksUseCase)
    }

    @Test
    fun testFetchAllStocks() {

        runBlocking {
            var stockEntity = StockEntity("Apple", "USD", 133.3)
            var stocks = mutableListOf<StockEntity>()
            stocks.add(stockEntity)
            val result = BaseResult.Success<List<StockEntity>>(stocks)
            val flow = flow<BaseResult<List<StockEntity>, StockResponse<MetaResponse, List<DataResponse>>>> {
                emit(result)
            }
            Mockito.`when`(getAllStocksUseCase.invoke()).thenReturn(flow)
            mainActivityViewModel.fetchAllStocks()
            verify(mainActivityViewModel, times(1)).fetchAllStocks()
        }

    }
}