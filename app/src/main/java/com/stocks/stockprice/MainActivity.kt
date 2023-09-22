package com.stocks.stockprice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.activity.viewModels
import androidx.compose.runtime.mutableStateListOf
import com.stocks.stockprice.R
import com.stocks.stockprice.adapter.AllStockRVAdapter
import com.stocks.stockprice.databinding.ActivityMainBinding
import com.stocks.stockprice.presentation.main.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.runtime.*
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.stocks.stockprice.domain.stocks.entity.StockEntity
import com.stocks.stockprice.presentation.main.MainActivityState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding

    private lateinit var viewModel: MainActivityViewModel

    private lateinit var adapter: AllStockRVAdapter

    val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = binding.toolbar

        setSupportActionBar(toolbar)
        fetchStocks()
        setRecyclerAdapter()
        observeState()

        Handler().postDelayed({

            observeStocks()

        },10000)




    }

    private fun setRecyclerAdapter(){
        adapter = AllStockRVAdapter(mutableStateListOf())
        binding.stockRv.adapter = adapter
        binding.stockRv.layoutManager = LinearLayoutManager(this)

    }

    fun observeState(){
        viewModel.mState.flowWithLifecycle(this.lifecycle, Lifecycle.State.STARTED).
                onEach { state -> handleState(state) }
            .launchIn(this.lifecycleScope)
    }

    private fun fetchStocks(){
        viewModel.fetchAllStocks()
    }

    private fun observeStocks(){
        viewModel.mStocks.flowWithLifecycle(this.lifecycle, Lifecycle.State.STARTED).
        onEach {
            Log.d(TAG, "observeStocks: " + it )
            handleStocks(it)
        }.
        launchIn(this.lifecycleScope)
    }

    private fun handleStocks(stocks: List<StockEntity>){
        val stockEntity = stocks.get(0)
        Log.d(TAG, "handleStocks: " + stockEntity )
        adapter?.updateList(stocks)
    }

    private fun handleState(state: MainActivityState){
        when(state){
            is MainActivityState.Init -> Unit
        }

    }


}