package com.example.paintdemo.basecharacteristic.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import com.example.paintdemo.BaseDrawActionView
import com.example.paintdemo.spToPx

class ShadowLayerDemoView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    isOpen: Boolean = false
) : BaseDrawActionView(context, attrs, defStyleAttr, isOpen) {

    override fun initPaint() {
        paint.textAlign = Paint.Align.CENTER
        paint.textSize = spToPx(20f)
    }

    override fun openCharacteristic() {
        paint.setShadowLayer(10f, 0f, 0f, Color.RED)
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawText("Crazy Coder",width/2f,height/2f,paint)
    }

}