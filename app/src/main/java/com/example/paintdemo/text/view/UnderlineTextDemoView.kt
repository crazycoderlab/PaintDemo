package com.example.paintdemo.text.view

import android.content.Context
import android.util.AttributeSet
import com.example.paintdemo.BaseDrawTextView

class UnderlineTextDemoView(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    isOpen: Boolean = true
) : BaseDrawTextView(context, attrs, defStyleAttr, isOpen) {
    override fun openCharacteristic() {
        paint.isUnderlineText = true
    }
}