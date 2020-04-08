package com.example.paintdemo.bitmap.view

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Rect
import android.util.AttributeSet
import com.example.paintdemo.BaseDrawActionView
import com.example.paintdemo.R

class BitmapFilterBitmapDemoView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0,isOpen: Boolean = true
) : BaseDrawActionView(context, attrs, defStyleAttr,isOpen) {

    lateinit var bitmap : Bitmap

    lateinit var destRect: Rect

    override fun openCharacteristic() {
        paint.isFilterBitmap = true
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        bitmap = BitmapFactory.decodeResource(resources, R.drawable.img4)
        destRect = Rect(0,0,w * 2,h * 2)
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawBitmap(bitmap,null,destRect,paint)
        canvas.translate((width - height) / 2f,height.toFloat())
    }

}