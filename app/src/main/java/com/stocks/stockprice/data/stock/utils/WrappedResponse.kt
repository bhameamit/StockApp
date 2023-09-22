package com.stocks.stockprice.data.stock.utils

import com.google.gson.annotations.SerializedName



data class MetaResponse (
    @SerializedName("requested") var requested: Int ,
    @SerializedName("returned") var returned: Int

)

data class DataResponse (

    @SerializedName("ticker") var ticker: String,
    @SerializedName("name") var name: String,
    @SerializedName("exchange_short") var exchange_short: String,
    @SerializedName("exchange_long") var exchange_long: String,
    @SerializedName("mic_code") var mic_code: String,
    @SerializedName("currency") var currency: String,
    @SerializedName("price") var price: Double,
    @SerializedName("day_high") var day_high: Double,
    @SerializedName("day_low") var day_low: Double,
    @SerializedName("day_open") var day_open: Double,
    @SerializedName("52_week_high") var week_high: Double,
    @SerializedName("52_week_low") var week_low: Double,
    @SerializedName("market_cap") var market_cap: Double,
    @SerializedName("previous_close_price") var previous_close_price: Double,
    @SerializedName("previous_close_price_time") var previous_close_price_time: String,
    @SerializedName("day_change") var day_change: Double,
    @SerializedName("volume") var volume: Int,
    @SerializedName("is_extended_hours_price") var is_extended_hours_price: Boolean,
    @SerializedName("last_trade_time") var last_trade_time: String,

)