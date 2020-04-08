package com.example.paintdemo.text.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import com.example.paintdemo.BaseDrawActionView
import com.example.paintdemo.BaseDrawTextView

class FakeBoldTextDemoView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0,isOpen: Boolean = true
) : BaseDrawTextView(context, attrs, defStyleAttr,isOpen) {

    override fun openCharacteristic() {
        paint.isFakeBoldText = true
        Log.v("FakeBoldTextDemoView","openCharacteristic")
    }

}