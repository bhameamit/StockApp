package com.stocks.stockprice.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stocks.stockprice.databinding.StockListBinding
import com.stocks.stockprice.domain.stocks.entity.StockEntity

class AllStockRVAdapter(private val stocks: MutableList<StockEntity>): RecyclerView.Adapter<AllStockRVAdapter.ViewHolder>() {

    val TAG = "AllStockRVAdapter"

    inner class ViewHolder(private val itemBinding: StockListBinding): RecyclerView.ViewHolder(itemBinding.root){
        fun bind(stock: StockEntity){
            var price:String = ""
            if(stock.currency.equals("USD"))
                price = "$" + stock.price.toString()
            itemBinding.stockName.text = stock.name
            itemBinding.stockPrice.text = price
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = StockListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(stocks[position])

    override fun getItemCount(): Int = stocks.size

    fun updateList(newStocks: List<StockEntity>){
        stocks.clear()
        stocks.addAll(newStocks)
        notifyDataSetChanged()
    }
}