package com.example.rippl

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.annotation.IdRes
import androidx.core.view.setPadding
import coil.load
import coil.transform.CircleCropTransformation
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable

class BuzzingStockView(context: Context, val attributeSet: AttributeSet) :
    FrameLayout(context, attributeSet) {

    private val stock = R.drawable.google_logo
    private val attrs by lazy {
        context.obtainStyledAttributes(
            attributeSet,
            R.styleable.BuzzingStockView
        )
    }
    val anchorId by lazy {
        attrs.getResourceId(R.styleable.BuzzingStockView_anchor, View.NO_ID)
    }

    init {
        runCheck()
        addView(getRippleView(), MATCH_PARENT, MATCH_PARENT)
        addView(getStockView(), MATCH_PARENT, MATCH_PARENT)
    }

    private fun getRippleView() = LottieAnimationView(context).apply {
        setAnimation(R.raw.ripple_anim)
        repeatCount = LottieDrawable.INFINITE
        playAnimation()
    }

    private fun getStockView() = ImageView(context).apply {
        load(stock) {
            transformations(CircleCropTransformation())
            // add placeholder and handle animation before placeholder
        }
        val inset = attrs.getDimension(R.styleable.BuzzingStockView_inset, 0f) /
                resources.displayMetrics.density
        attrs.recycle()
        setPadding(inset.dp())
        setBackgroundResource(R.drawable.bg_circle)
        layoutParams = LayoutParams(MATCH_PARENT, MATCH_PARENT)
        clipToOutline = true
    }

    private fun runCheck() {
        // check if anchor attribute is added
        if (anchorId == View.NO_ID) throw RuntimeException("No anchor set for the View")
    }

    /**
     * Take dp and return the value in pixels
     * */
    private fun Float.dp(): Int {
        val scale = context.resources.displayMetrics.density
        return (this * scale + 0.5f).toInt()
    }
}