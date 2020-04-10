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
    isOpen: Boolean = true
) : BaseDrawActionView(context, attrs, defStyleAttr, isOpen) {

    private var bitmap: Bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(resources,R.drawable.img2),
        dpToPx(100f).toInt(),dpToPx(100f).toInt(),true)

    var blurMode = 0
    override fun initSpecialConfig(attrs: AttributeSet?, defStyleAttr: Int) {
        val typeArray = context.obtainStyledAttributes(attrs,R.styleable.MaskFilterDemoView,defStyleAttr,0)
        blurMode = typeArray.getInt(R.styleable.MaskFilterDemoView_blurMode,0)
        typeArray.recycle()
    }

    override fun openCharacteristic() {
        val blur = when(blurMode){
            BlurMaskFilter.Blur.NORMAL.ordinal -> BlurMaskFilter.Blur.NORMAL
            BlurMaskFilter.Blur.SOLID.ordinal -> BlurMaskFilter.Blur.SOLID
            BlurMaskFilter.Blur.OUTER.ordinal -> BlurMaskFilter.Blur.OUTER
            else -> BlurMaskFilter.Blur.INNER
        }
        paint.maskFilter = BlurMaskFilter(dpToPx(30f), blur)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec),bitmap.height + 100)
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawBitmap(bitmap,width/2f - bitmap.width/2f,height/2f - bitmap.height/2f,paint)
    }

}