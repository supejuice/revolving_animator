package com.example.rippl

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout

class BuzzingStocksLayout(context: Context, attrs: AttributeSet) : FrameLayout(context, attrs) {
    override fun addView(child: View?) {
        super.addView(child)
    }

    override fun addView(child: View?, index: Int) {
        super.addView(child, index)
    }
}