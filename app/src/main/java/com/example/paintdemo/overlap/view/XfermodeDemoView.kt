package com.example.paintdemo.overlap.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import com.example.paintdemo.BaseDrawActionView
import com.example.paintdemo.dpToPx

class XfermodeDemoView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0,isOpen: Boolean = true
) : BaseDrawActionView(context, attrs, defStyleAttr,isOpen) {

    var mode = 0
        set(value) {
            field = value
            setUpXfermode()
        }

    lateinit var bitmapCircle: Bitmap
    lateinit var bitmapRect: Bitmap

    val paintNoXfermode = Paint()

    override fun openCharacteristic() {
    }

    private fun setUpXfermode(){
        when(mode){
            PorterDuff.Mode.CLEAR.ordinal -> paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
            PorterDuff.Mode.SRC.ordinal -> paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC)
            PorterDuff.Mode.DST.ordinal -> paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.DST)
            PorterDuff.Mode.SRC_OVER.ordinal -> paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_OVER)
            PorterDuff.Mode.DST_OVER.ordinal -> paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_OVER)
            PorterDuff.Mode.SRC_IN.ordinal -> paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
            PorterDuff.Mode.DST_IN.ordinal -> paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_IN)
            PorterDuff.Mode.SRC_OUT.ordinal -> paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_OUT)
            PorterDuff.Mode.DST_OUT.ordinal -> paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_OUT)
            PorterDuff.Mode.SRC_ATOP.ordinal -> paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP)
            PorterDuff.Mode.DST_ATOP.ordinal -> paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_ATOP)
            PorterDuff.Mode.XOR.ordinal -> paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.XOR)
            PorterDuff.Mode.DARKEN.ordinal -> paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.DARKEN)
            PorterDuff.Mode.LIGHTEN.ordinal -> paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.LIGHTEN)
            PorterDuff.Mode.MULTIPLY.ordinal -> paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.MULTIPLY)
            PorterDuff.Mode.SCREEN.ordinal -> paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SCREEN)
            PorterDuff.Mode.ADD.ordinal -> paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.ADD)
            PorterDuff.Mode.OVERLAY.ordinal -> paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.OVERLAY)
        }
        invalidate()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        paint.blendMode = null

        bitmapCircle = Bitmap.createBitmap(dpToPx(60f).toInt(), dpToPx(60f).toInt(), Bitmap.Config.RGB_565)
        val canvasCircle = Canvas(bitmapCircle)
        paint.color = Color.RED
        canvasCircle.drawRect(0f,0f,bitmapCircle.width.toFloat(),bitmapCircle.height.toFloat(),paint)

        bitmapRect = Bitmap.createBitmap(dpToPx(60f).toInt(), dpToPx(60f).toInt(), Bitmap.Config.RGB_565)
        val canvasRect= Canvas(bitmapRect)
        paint.color = Color.BLUE
        canvasRect.drawRect(0f,0f,bitmapRect.width.toFloat(),bitmapRect.height.toFloat(),paint)
    }

    override fun onDraw(canvas: Canvas) {
        canvas.save()
        canvas.drawBitmap(bitmapCircle,width/2f - bitmapCircle.width/2f, height/2f - bitmapCircle.height/2f,paintNoXfermode)
        canvas.drawBitmap(bitmapRect,width/2f, height/2f - bitmapCircle.height,paint)
        canvas.restore()
    }

}