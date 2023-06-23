package com.example.rippl

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.rippl.databinding.ActivityMainBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlin.coroutines.coroutineContext

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val buzzingStocksAnimator by lazy { BuzzingStocksAnimator(binding.stocks) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        lifecycleScope.launch {
            rotateStocks()
        }
    }

    private suspend fun rotateStocks() {
        delay(3000)
        buzzingStocksAnimator.start()
        if (coroutineContext.isActive)
            rotateStocks()
    }
}