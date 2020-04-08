package com.example.paintdemo.line.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import com.example.paintdemo.BaseDrawActionView
import com.example.paintdemo.R
import com.example.paintdemo.dpToPx

class LineCapDemoView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0,isOpen: Boolean = true
) : BaseDrawActionView(context, attrs, defStyleAttr,isOpen) {

    private val lineLength = dpToPx(80f)

    private var capStyle: Int = -1

    override fun initSpecialConfig(attrs: AttributeSet?, defStyleAttr: Int) {
        val typeArray = context.obtainStyledAttributes(attrs, R.styleable.LineCapDemoView,defStyleAttr,0)
        capStyle = typeArray.getInt(R.styleable.LineCapDemoView_capStyle,-1)
        typeArray.recycle()
    }

    override fun initPaint() {
        paint.strokeWidth = 55f
        paint.color = Color.RED
    }

    override fun openCharacteristic() {
        when(capStyle){
            Paint.Cap.BUTT.ordinal -> paint.strokeCap = Paint.Cap.BUTT
            Paint.Cap.ROUND.ordinal -> paint.strokeCap = Paint.Cap.ROUND
            Paint.Cap.SQUARE.ordinal -> paint.strokeCap = Paint.Cap.SQUARE
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawLine(width/2f - lineLength/2f,height/2f,width/2f + lineLength/2f,height/2f,paint)
    }

}