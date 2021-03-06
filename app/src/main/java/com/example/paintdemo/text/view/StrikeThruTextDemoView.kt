package com.example.paintdemo.text.view

import android.content.Context
import android.util.AttributeSet
import com.example.paintdemo.BaseDrawTextView

class StrikeThruTextDemoView(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    isOpen: Boolean = true
) : BaseDrawTextView(context, attrs, defStyleAttr, isOpen) {
    override fun openCharacteristic() {
        paint.isStrikeThruText = true
    }
}