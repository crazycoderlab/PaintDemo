package com.example.paintdemo.line.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import com.example.paintdemo.BaseDrawActionView
import com.example.paintdemo.R

class LineJoinDemoView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0,isOpen: Boolean = true
) : BaseDrawActionView(context, attrs, defStyleAttr,isOpen) {

    private var joinStyle: Int = -1

    override fun initSpecialConfig(attrs: AttributeSet?, defStyleAttr: Int) {
        val typeArray = context.obtainStyledAttributes(attrs, R.styleable.LineJoinDemoView,defStyleAttr,0)
        joinStyle = typeArray.getInt(R.styleable.LineJoinDemoView_joinStyle,-1)
        typeArray.recycle()
    }

    override fun initPaint() {
        paint.strokeWidth = 35f
        paint.color = Color.RED
        paint.style = Paint.Style.STROKE
    }

    override fun openCharacteristic() {
        when(joinStyle){
            Paint.Join.MITER.ordinal -> paint.strokeJoin = Paint.Join.MITER
            Paint.Join.ROUND.ordinal -> paint.strokeJoin = Paint.Join.ROUND
            Paint.Join.BEVEL.ordinal -> paint.strokeJoin = Paint.Join.BEVEL
        }
    }

    private val drawPath = Path()
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        drawPath.reset()
        drawPath.moveTo(width/4f,height/2f)
        drawPath.lineTo(width/4f * 3,height/2f)
        drawPath.lineTo(width/2f,height/6f * 5)
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawPath(drawPath,paint)
//        canvas.drawLines(floatArrayOf(width/4f,height/2f,width/4f * 3,height/2f,
//            width/4f * 3,height/2f,width/2f,height/6f * 4),paint)
    }

}