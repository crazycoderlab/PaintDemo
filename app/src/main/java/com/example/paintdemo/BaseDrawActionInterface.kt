package com.example.paintdemo

import android.content.Context
import android.content.res.Resources
import android.graphics.Paint
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView

interface BaseDrawActionInterface {
    fun openCharacteristic()
}

abstract class BaseDrawActionView  : View,BaseDrawActionInterface{


    constructor(context: Context): super(context,null,0)

    constructor(context: Context,attrs: AttributeSet?): super(context,attrs,0)

    constructor(context: Context,attrs: AttributeSet?,defStyleAttr: Int): super(context,attrs,defStyleAttr)

    constructor(context: Context,attrs: AttributeSet?,defStyleAttr: Int, isOpen: Boolean): super(context,attrs,defStyleAttr){
        if(isOpen) {
            openCharacteristic()
        }
        initPaint()
    }

    protected val paint = Paint()

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), dpToPx(150f).toInt())
    }

    protected open fun initPaint(){

    }

}

class MyPaintDemoLinearLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    var inflateChildViewClassName: String? = null
    var demoTypeName: String? = null

    init {
        orientation = VERTICAL
        gravity = Gravity.CENTER

        val typeArray = context.obtainStyledAttributes(attrs,R.styleable.MyPaintDemoLinearLayout,defStyleAttr,0)

        inflateChildViewClassName =
            typeArray.getString(R.styleable.MyPaintDemoLinearLayout_inflateChildViewClassName)

        typeArray.getString(R.styleable.MyPaintDemoLinearLayout_demoTypeName)?.let {
            demoTypeName = it
        }

        typeArray.recycle()

        addChildView()
    }

    private fun addChildView(){
        inflateChildViewClassName?.run {
            val childViewClass = Class.forName(inflateChildViewClassName!!)
            val constructor = childViewClass.getDeclaredConstructor(Context::class.java,AttributeSet::class.java,Int::class.java,Boolean::class.java)
            val childOpen = constructor.newInstance(context,null,0,true) as View
            val childUnOpen = constructor.newInstance(context,null,0,false) as View

            val layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT)
            layoutParams.topMargin = 20
            childOpen.layoutParams = layoutParams
            childUnOpen.layoutParams = layoutParams

            addView(childOpen)
            addView(childUnOpen)
        }

        demoTypeName?.run {
            val titleTextView = TextView(context)
            val layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT)
            titleTextView.text = demoTypeName
            titleTextView.textSize = 20f
            titleTextView.layoutParams = layoutParams
            addView(titleTextView,0)
        }

    }

}

fun dpToPx(dp: Float): Float{
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp, Resources.getSystem().displayMetrics)
}

fun spToPx(sp: Float): Float{
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,sp, Resources.getSystem().displayMetrics)
}
