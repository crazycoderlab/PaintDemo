package com.example.paintdemo.color

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import com.example.paintdemo.BaseDrawActionView
import com.example.paintdemo.R
import com.example.paintdemo.dpToPx

class ShaderDemoView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    private val isOpen: Boolean = true
) : BaseDrawActionView(context, attrs, defStyleAttr, isOpen) {

    private var shaderType = 1
    private var shaderMode = 1

    private lateinit var tileMode: Shader.TileMode
    private lateinit var bitmap: Bitmap

    override fun initSpecialConfig(attrs: AttributeSet?, defStyleAttr: Int) {
        val typeArray =
            context.obtainStyledAttributes(attrs, R.styleable.ShaderDemoView, defStyleAttr, 0)
        shaderType = typeArray.getInt(R.styleable.ShaderDemoView_shaderType, 1)
        shaderMode = typeArray.getInt(R.styleable.ShaderDemoView_shaderMode, 0)
        typeArray.recycle()
        Log.v("ShaderDemoView", "initSpecialConfig shaderType = $shaderType")
    }

    override fun openCharacteristic() {
        tileMode = when (shaderMode) {
            Shader.TileMode.CLAMP.ordinal -> {
                Shader.TileMode.CLAMP
            }
            Shader.TileMode.REPEAT.ordinal -> {
                Shader.TileMode.REPEAT
            }
            else -> {
                Shader.TileMode.MIRROR
            }
        }
        if (shaderType == 4) {
            bitmap = BitmapFactory.decodeResource(resources, R.drawable.img2)
        }
        tag = shaderType
        Log.v("ShaderDemoView", "openCharacteristic shaderType = $shaderType")
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        val myTag = tag as Int
        if (isOpen) {
            when (myTag) {
                1 -> paint.shader = LinearGradient(
                    0f, height / 2f, width / 3f, height / 2f,
                    Color.parseColor("#E91E63"),
                    Color.parseColor("#2196F3"),
                    tileMode
                )
                2 -> paint.shader = SweepGradient(
                    width / 2f, height / 2f, Color.parseColor("#E91E63"),
                    Color.parseColor("#2196F3")
                )
                3 -> paint.shader = RadialGradient(
                    width / 2f, height / 2f, dpToPx(20f),
                    Color.parseColor("#E91E63"),
                    Color.parseColor("#2196F3"),
                    tileMode
                )
                4 -> paint.shader = BitmapShader(
                    Bitmap.createScaledBitmap(bitmap, width / 2, height / 2, true),
                    tileMode, tileMode
                )
            }
        }
        Log.v("ShaderDemoView", "onSizeChanged myTag = $myTag")
    }

    override fun onDraw(canvas: Canvas) {
        val myTag = tag as Int
        when (myTag) {
            1, 4 -> canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), paint)
            2, 3 -> canvas.drawCircle(width / 2f, height / 2f, dpToPx(60f), paint)
        }
        Log.v("ShaderDemoView", "onDraw myTag = $myTag")
    }

}