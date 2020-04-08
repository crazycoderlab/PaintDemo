package com.example.paintdemo.basecharacteristic.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import com.example.paintdemo.BaseDrawActionView
import com.example.paintdemo.R
import com.example.paintdemo.dpToPx

class StyleDemoView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    isOpen: Boolean = true
) : BaseDrawActionView(context, attrs, defStyleAttr, isOpen) {

    private var styleType: Int = 0

    override fun initSpecialConfig(attrs: AttributeSet?, defStyleAttr: Int) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.StyleDemoView,defStyleAttr,0)
        styleType = typedArray.getInt(R.styleable.StyleDemoView_styleType,0)
        typedArray.recycle()
    }

    override fun initPaint() {
        paint.strokeWidth = 65f
        paint.color = Color.RED
    }

    override fun openCharacteristic() {
        when(styleType){
            1 -> paint.style = Paint.Style.STROKE
            2 -> paint.style = Paint.Style.FILL
            3 -> paint.style = Paint.Style.FILL_AND_STROKE
        }
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawCircle(width/2f,height/2f, dpToPx(50f),paint)
    }

}