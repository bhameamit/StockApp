package com.stocks.stockprice.data.stock

import com.stocks.stockprice.data.network.NetworkModule
import com.stocks.stockprice.data.stock.interfaces.StockAPI
import com.stocks.stockprice.data.stock.repository.StockRepositoryImpl
import com.stocks.stockprice.domain.stocks.interfaces.StockRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
class StockModule {

    @Singleton
    @Provides
    fun provideStockAPI(retrofit: Retrofit): StockAPI{
        return retrofit.create(StockAPI::class.java)
    }

    @Singleton
    @Provides
    fun provideStockRepository(stockAPI: StockAPI): StockRepository{
        return StockRepositoryImpl(stockAPI)
    }

}