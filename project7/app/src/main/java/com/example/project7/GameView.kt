package com.example.project7

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.View
import android.widget.RelativeLayout

class GameView : View {
    private var x : Int = 0
    private var y : Int = 0
    private var radius : Int = 0
    private lateinit var paint : Paint
    private lateinit var balloons : Balloons
    private lateinit var rl: RelativeLayout

    constructor(context : Context, x : Int, y : Int, rad : Int) : super (context) {
        paint = Paint()
        paint.color = Color.BLUE
        paint.strokeWidth = 20.0f
        paint.isAntiAlias = true

        this.x = x
        this.y = y
        this.radius = rad

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawCircle(x.toFloat() , y.toFloat(), radius.toFloat(), paint)
    }
}