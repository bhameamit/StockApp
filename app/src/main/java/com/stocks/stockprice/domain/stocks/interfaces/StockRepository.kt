package com.stocks.stockprice.domain.stocks.interfaces

import com.stocks.stockprice.data.stock.utils.DataResponse
import com.stocks.stockprice.data.stock.utils.MetaResponse
import com.stocks.stockprice.data.stock.utils.StockResponse
import com.stocks.stockprice.domain.common.BaseResult
import com.stocks.stockprice.domain.stocks.entity.StockEntity
import kotlinx.coroutines.flow.Flow

interface StockRepository {
    suspend fun getAllStocks(): Flow<BaseResult<List<StockEntity>, StockResponse<MetaResponse, List<DataResponse>>>>
}