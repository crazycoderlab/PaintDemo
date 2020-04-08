package com.example.paintdemo.bitmap.view

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Rect
import android.util.AttributeSet
import com.example.paintdemo.BaseDrawActionView
import com.example.paintdemo.R

class BitmapDitherDemoView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0,isOpen: Boolean = true
) : BaseDrawActionView(context, attrs, defStyleAttr,isOpen) {

    lateinit var bitmap : Bitmap

    lateinit var destRect: Rect

    override fun openCharacteristic() {
        paint.isDither = true
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        val option = BitmapFactory.Options()
        option.inPreferredConfig = Bitmap.Config.ARGB_4444
        bitmap = BitmapFactory.decodeResource(resources,R.drawable.img3,option)
        destRect = Rect(0,0,w,h)
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawBitmap(bitmap,null,destRect,paint)
    }

}