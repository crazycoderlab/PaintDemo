package com.example.paintdemo

import android.content.Context
import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.Space
import android.widget.TextView

interface BaseDrawActionInterface {
    fun openCharacteristic()
}

abstract class BaseDrawActionView : View, BaseDrawActionInterface {

    constructor(context: Context) : super(context, null, 0)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ){
        initAll(attrs,defStyleAttr,false)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, isOpen: Boolean) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initAll(attrs,defStyleAttr,isOpen)
    }

    protected val paint = Paint()

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), dpToPx(150f).toInt())
    }

    private fun initAll(attrs: AttributeSet?, defStyleAttr: Int,isOpen: Boolean){
        Log.v("BaseDrawActionView","isOpen = $isOpen")
        initPaint()
        initSpecialConfig(attrs,defStyleAttr)
        if (isOpen) {
            openCharacteristic()
        }
    }

    protected open fun initPaint() {

    }

    protected open fun initSpecialConfig(attrs: AttributeSet?, defStyleAttr: Int){

    }

}

abstract class BaseDrawTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0,isOpen: Boolean = true
) : BaseDrawActionView(context, attrs, defStyleAttr,isOpen) {

    override fun initPaint() {
        paint.textAlign = Paint.Align.CENTER
        paint.textSize = spToPx(16f)
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawText("Crazy Coder 安卓开发",width/2f,height/2f,paint)
    }

}

class MyPaintDemoLinearLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    var inflateChildViewClassName: String? = null
    var openInfo: String? = null
    var unOpenInfo: String? = null
    var isAutoAddUnOpenChild: Boolean = true

    init {
        orientation = VERTICAL
        gravity = Gravity.CENTER

        val typeArray = context.obtainStyledAttributes(
            attrs,
            R.styleable.MyPaintDemoLinearLayout,
            defStyleAttr,
            0
        )

        inflateChildViewClassName =
            typeArray.getString(R.styleable.MyPaintDemoLinearLayout_inflateChildViewClassName)

        typeArray.getString(R.styleable.MyPaintDemoLinearLayout_openInfo)?.let {
            openInfo = it
        }

        typeArray.getString(R.styleable.MyPaintDemoLinearLayout_unOpenInfo)?.let {
            unOpenInfo = it
        }

        isAutoAddUnOpenChild = typeArray.getBoolean(R.styleable.MyPaintDemoLinearLayout_isAutoAddUnOpenChild,true)

        typeArray.recycle()

        addChildView()
    }

    private fun addChildView() {
        inflateChildViewClassName?.let {
            val childViewClass = Class.forName(inflateChildViewClassName!!)
            val constructor = childViewClass.getDeclaredConstructor(
                Context::class.java,
                AttributeSet::class.java,
                Int::class.java,
                Boolean::class.java
            )

            val layoutParamsWrapContent = LayoutParams(0, LayoutParams.WRAP_CONTENT)
            layoutParamsWrapContent.weight = 1f

            val layoutParamsChildView = LayoutParams(0, LayoutParams.WRAP_CONTENT)
            layoutParamsChildView.weight = 2f

            val childOpenLinearLayout = LinearLayout(context)
            childOpenLinearLayout.orientation = HORIZONTAL
            childOpenLinearLayout.gravity = Gravity.CENTER_VERTICAL
            val childOpen = constructor.newInstance(context, null, 0, true) as View
            val childOpenTextView = TextView(context)
            childOpenTextView.layoutParams = layoutParamsWrapContent
            childOpenTextView.setPadding(
                dpToPx(15f).toInt(),
                dpToPx(15f).toInt(),
                dpToPx(15f).toInt(),
                dpToPx(15f).toInt()
            )
            childOpenTextView.textSize = 16f
            childOpenTextView.gravity = Gravity.CENTER
            if (!openInfo.isNullOrEmpty()) {
                childOpenTextView.text = openInfo
            } else {
                childOpenTextView.text = "开启效果"
            }
            childOpenLinearLayout.addView(childOpenTextView)
            childOpen.layoutParams = layoutParamsChildView
            childOpenLinearLayout.addView(childOpen)
            addView(childOpenLinearLayout)

            if(isAutoAddUnOpenChild){
                val line = Space(context)
                line.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, dpToPx(10f).toInt())
                addView(line)
                val childUnOpenLinearLayout = LinearLayout(context)
                childUnOpenLinearLayout.orientation = HORIZONTAL
                childUnOpenLinearLayout.gravity = Gravity.CENTER_VERTICAL
                val childUnOpen = constructor.newInstance(context, null, 0, false) as View
                val childUnOpenTextView = TextView(context)
                childUnOpenTextView.layoutParams = layoutParamsWrapContent
                childUnOpenTextView.setPadding(
                    dpToPx(15f).toInt(),
                    dpToPx(15f).toInt(),
                    dpToPx(15f).toInt(),
                    dpToPx(15f).toInt()
                )
                childUnOpenTextView.gravity = Gravity.CENTER
                childUnOpenTextView.textSize = 16f
                if (!unOpenInfo.isNullOrEmpty()) {
                    childUnOpenTextView.text = unOpenInfo
                } else {
                    childUnOpenTextView.text = "未开效果"
                }
                childUnOpenLinearLayout.addView(childUnOpenTextView)
                childUnOpen.layoutParams = layoutParamsChildView
                childUnOpenLinearLayout.addView(childUnOpen)
                addView(childUnOpenLinearLayout)
            }

            val line = Space(context)
            line.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, dpToPx(10f).toInt())
            addView(line)
        }
    }

}

fun dpToPx(dp: Float): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dp,
        Resources.getSystem().displayMetrics
    )
}

fun spToPx(sp: Float): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_SP,
        sp,
        Resources.getSystem().displayMetrics
    )
}
