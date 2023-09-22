package com.stocks.stockprice.domain.stocks.usecase

import com.stocks.stockprice.data.stock.utils.DataResponse
import com.stocks.stockprice.data.stock.utils.MetaResponse
import com.stocks.stockprice.data.stock.utils.StockResponse
import com.stocks.stockprice.domain.common.BaseResult
import com.stocks.stockprice.domain.stocks.entity.StockEntity
import com.stocks.stockprice.domain.stocks.interfaces.StockRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetAllStocksUseCase @Inject constructor(private val stockRepository: StockRepository){
    suspend fun invoke() : Flow<BaseResult<List<StockEntity>, StockResponse<MetaResponse, List<DataResponse>>>> {
        return stockRepository.getAllStocks()
    }
}