package com.example.paintdemo.basecharacteristic.view

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.BlurMaskFilter
import android.graphics.Canvas
import android.util.AttributeSet
import com.example.paintdemo.BaseDrawActionView
import com.example.paintdemo.R
import com.example.paintdemo.dpToPx

class MaskFilterDemoView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    isOpen: Boolean = false
) : BaseDrawActionView(context, attrs, defStyleAttr, isOpen) {

    private var bitmap: Bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(resources,R.drawable.img2),
        dpToPx(100f).toInt(),dpToPx(100f).toInt(),true)

    override fun openCharacteristic() {
        paint.maskFilter = BlurMaskFilter(dpToPx(30f), BlurMaskFilter.Blur.NORMAL)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec),bitmap.height + 100)
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawBitmap(bitmap,width/2f - bitmap.width/2f,height/2f - bitmap.height/2f,paint)
    }

}