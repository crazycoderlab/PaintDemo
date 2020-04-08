package com.example.paintdemo.text.view

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import com.example.paintdemo.BaseDrawTextView

class LetterSpacingDemoView(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    isOpen: Boolean = true
) : BaseDrawTextView(context, attrs, defStyleAttr, isOpen) {
    override fun openCharacteristic() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            paint.letterSpacing = 0.2f
        }
    }

}