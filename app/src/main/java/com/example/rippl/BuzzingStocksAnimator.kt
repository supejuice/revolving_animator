package com.example.rippl

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.PathInterpolator
import androidx.core.view.children

class BuzzingStocksAnimator(private val viewGroup: ViewGroup) {

    fun start() {
        viewGroup.children.forEachIndexed { _, child ->
            val view = child as? BuzzingStockView
                ?: throw RuntimeException("Children should only be a type of ${BuzzingStockView::class.simpleName}")
            val anchorId = view.anchorId
            val destinationView = viewGroup.findViewById<BuzzingStockView>(anchorId)
                ?: throw RuntimeException("View does not have an anchor $anchorId")
            animate(view, destinationView)
        }
    }

    fun animate(view: ViewGroup, destinationView: ViewGroup) {
        /*val xHolder = PropertyValuesHolder.ofFloat(
            "x",
            startLocation[0].toFloat(),
            destinationLocation[0].toFloat()
        )
        val yHolder = PropertyValuesHolder.ofFloat(
            "y",
            startLocation[1].toFloat(),
            destinationLocation[1].toFloat()
        )*/
        view.animate()
            .x(destinationView.x)
            .y(destinationView.y)
            .scaleX(destinationView.scaleX)
            .scaleY(destinationView.scaleY)
            .setInterpolator(AccelerateDecelerateInterpolator())
            .setDuration(1000)
            .start()
        /*ObjectAnimator.ofPropertyValuesHolder(view, xHolder, yHolder)
            .apply {
                duration = 1000
//                interpolator = pathInterpolator
                interpolator = AccelerateDecelerateInterpolator()
                start()
            }*/
    }

}