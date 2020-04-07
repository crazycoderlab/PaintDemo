package com.example.paintdemo.basecharacteristic.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.DashPathEffect
import android.graphics.PathEffect
import android.util.AttributeSet
import com.example.paintdemo.BaseDrawActionView


class PathEffectDemoView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    isOpen: Boolean = false
) : BaseDrawActionView(context, attrs, defStyleAttr, isOpen) {

    override fun initPaint() {
        paint.strokeWidth = 15f
    }

    override fun openCharacteristic() {
        //第一个参数 intervals 是一个数组，它指定了虚线的格式：
        // 数组中元素必须为偶数（最少是 2 个），按照「画线长度、空白长度、画线长度、空白长度」……的顺序排列，
        // 例如下面代码中的 10, 5 就表示虚线是按照「画 10 像素、空 5 像素、画 10 像素、空 5 像素...」的模式来绘制；
        // 第二个参数 phase 是虚线的偏移量。
        val pathEffect: PathEffect = DashPathEffect(floatArrayOf(10f, 5f), 10f)
        paint.pathEffect = pathEffect
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawLine(0f,height/2f,width.toFloat(),height/2f,paint)
    }


}