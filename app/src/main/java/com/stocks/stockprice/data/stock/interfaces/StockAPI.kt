package com.stocks.stockprice.data.stock.interfaces

import com.stocks.stockprice.data.stock.utils.DataResponse
import com.stocks.stockprice.data.stock.utils.MetaResponse
import com.stocks.stockprice.data.stock.utils.StockResponse
import com.stocks.stockprice.domain.common.BaseResult
import com.stocks.stockprice.domain.stocks.entity.StockEntity
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface StockAPI {
    @GET("quote?symbols=AAPL,TSLA&api_token=XCgmh9ZXfumrL3mVVWfYLk1SRdxKCsSfXIzjRH9M")
    suspend fun getAllStocks(): StockResponse<MetaResponse, List<DataResponse>>
}