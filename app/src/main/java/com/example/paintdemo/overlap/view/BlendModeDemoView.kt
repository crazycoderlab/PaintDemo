package com.example.paintdemo.overlap.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import com.example.paintdemo.BaseDrawActionView
import com.example.paintdemo.dpToPx

class BlendModeDemoView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0,isOpen: Boolean = true
) : BaseDrawActionView(context, attrs, defStyleAttr,isOpen) {

    var mode = 0
        set(value) {
            field = value
            setUpBlendMode()
        }

    lateinit var bitmapCircle: Bitmap
    lateinit var bitmapRect: Bitmap

    val paintNoBlend = Paint()

    override fun openCharacteristic() {
    }

    private fun setUpBlendMode(){
        when(mode){
            BlendMode.CLEAR.ordinal -> paint.blendMode = BlendMode.CLEAR
            BlendMode.SRC.ordinal -> paint.blendMode = BlendMode.SRC
            BlendMode.DST.ordinal -> paint.blendMode = BlendMode.DST
            BlendMode.SRC_OVER.ordinal -> paint.blendMode = BlendMode.SRC_OVER
            BlendMode.DST_OVER.ordinal -> paint.blendMode = BlendMode.DST_OVER
            BlendMode.SRC_IN.ordinal -> paint.blendMode = BlendMode.SRC_IN
            BlendMode.DST_IN.ordinal -> paint.blendMode = BlendMode.DST_IN
            BlendMode.SRC_OUT.ordinal -> paint.blendMode = BlendMode.SRC_OUT
            BlendMode.DST_OUT.ordinal -> paint.blendMode = BlendMode.DST_OUT
            BlendMode.SRC_ATOP.ordinal -> paint.blendMode = BlendMode.SRC_ATOP
            BlendMode.DST_ATOP.ordinal -> paint.blendMode = BlendMode.DST_ATOP
            BlendMode.XOR.ordinal -> paint.blendMode = BlendMode.XOR
            BlendMode.PLUS.ordinal -> paint.blendMode = BlendMode.PLUS
            BlendMode.MODULATE.ordinal -> paint.blendMode = BlendMode.MODULATE
            BlendMode.SCREEN.ordinal -> paint.blendMode = BlendMode.SCREEN
            BlendMode.OVERLAY.ordinal -> paint.blendMode = BlendMode.OVERLAY
            BlendMode.DARKEN.ordinal -> paint.blendMode = BlendMode.DARKEN
            BlendMode.LIGHTEN.ordinal -> paint.blendMode = BlendMode.LIGHTEN
            BlendMode.COLOR_DODGE.ordinal -> paint.blendMode = BlendMode.COLOR_DODGE
            BlendMode.COLOR_BURN.ordinal -> paint.blendMode = BlendMode.COLOR_BURN
            BlendMode.HARD_LIGHT.ordinal -> paint.blendMode = BlendMode.HARD_LIGHT
            BlendMode.SOFT_LIGHT.ordinal -> paint.blendMode = BlendMode.SOFT_LIGHT
            BlendMode.DIFFERENCE.ordinal -> paint.blendMode = BlendMode.DIFFERENCE
            BlendMode.EXCLUSION.ordinal -> paint.blendMode = BlendMode.EXCLUSION
            BlendMode.MULTIPLY.ordinal -> paint.blendMode = BlendMode.MULTIPLY
            BlendMode.HUE.ordinal -> paint.blendMode = BlendMode.HUE
            BlendMode.SATURATION.ordinal -> paint.blendMode = BlendMode.SATURATION
            BlendMode.COLOR.ordinal -> paint.blendMode = BlendMode.COLOR
            BlendMode.LUMINOSITY.ordinal -> paint.blendMode = BlendMode.LUMINOSITY
        }
        invalidate()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        paint.blendMode = null

        bitmapCircle = Bitmap.createBitmap(dpToPx(60f).toInt(),dpToPx(60f).toInt(),Bitmap.Config.RGB_565)
        val canvasCircle = Canvas(bitmapCircle)
        paint.color = Color.RED
        canvasCircle.drawRect(0f,0f,bitmapCircle.width.toFloat(),bitmapCircle.height.toFloat(),paint)

        bitmapRect = Bitmap.createBitmap(dpToPx(60f).toInt(),dpToPx(60f).toInt(),Bitmap.Config.RGB_565)
        val canvasRect= Canvas(bitmapRect)
        paint.color = Color.BLUE
        canvasRect.drawRect(0f,0f,bitmapRect.width.toFloat(),bitmapRect.height.toFloat(),paint)

    }

    override fun onDraw(canvas: Canvas) {
        canvas.save()
        canvas.drawBitmap(bitmapCircle,width/2f - bitmapCircle.width/2f, height/2f - bitmapCircle.height/2f,paintNoBlend)
        canvas.drawBitmap(bitmapRect,width/2f, height/2f - bitmapCircle.height,paint)
        canvas.restore()
    }

}