package com.example.paintdemo.basecharacteristic.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.util.AttributeSet
import com.example.paintdemo.BaseDrawActionView
import com.example.paintdemo.dpToPx

class AntiAliasDemoView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    isOpen: Boolean = false
) : BaseDrawActionView(context, attrs, defStyleAttr, isOpen) {

    override fun initPaint() {
        paint.color = Color.RED
    }

    override fun openCharacteristic() {
        paint.isAntiAlias = true
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawCircle(width/2f,height/2f, dpToPx(50f),paint)
    }

}